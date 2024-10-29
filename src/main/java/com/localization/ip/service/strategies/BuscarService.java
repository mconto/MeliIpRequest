package com.localization.ip.service.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.localization.ip.model.Currency;
import com.localization.ip.model.Distancias;
import com.localization.ip.model.ExchangeRateResponse;
import com.localization.ip.model.IpInfo;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.DataRequest;
import com.localization.ip.service.OpcionesService;
import com.localization.ip.util.GeoLocalization;
import com.localization.ip.util.Propiedades;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier("BuscarService")
@AllArgsConstructor
public class BuscarService implements OpcionesService {

    private final String ACCESS_KEY = "access_key";
    private final String SIMBOLS = "simbols";

    private final String BUSCAR = "BUSCAR";

    private final RestTemplate restTemplate;

    @Autowired
    DataRequest dataRequest;

    @Autowired
    Propiedades propiedades;

    @Autowired
    DistanciasService distanciasService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GeoLocalization geoLocalization;

    @Override
    public ResponseModel response(String ip) throws JsonProcessingException {
        DecimalFormat df = new DecimalFormat("#,###");
        IpInfo ipInfo = devolverIpInfo(ip);

        if (Objects.isNull(ipInfo.getCurrency())){
            Currency currency = new Currency();
            currency.setCode("COP");
            ipInfo.setCurrency(currency);
        }

        String localCurrency = ipInfo.getCurrency().getCode();

        List<String> formattedLanguage = new ArrayList<>();
        ipInfo.getLocation().getLanguages().forEach(language -> {
            formattedLanguage.add(String.format("%s (%s)", language.getNativeLanguage(), language.getCode()));
        });

        Double monedaLocalEnUsd = devolverMonedaLocal(localCurrency);
        String formatMoney = df.format(monedaLocalEnUsd);
        monedaLocalEnUsd = Double.parseDouble(formatMoney);
        Double distancia = geoLocalization.calcularDistancia(ipInfo.getLongitude(), ipInfo.getLatitude());
        String formatDistancia = df.format(distancia);
        distancia = Double.parseDouble(formatDistancia);

        distanciasService.almacenarDistancias(Distancias.builder().distancia(distancia).pais(ipInfo.getCountry_name()).build());

        return ResponseModel
                .builder()
                .operacion(BUSCAR)
                .ip(ip)
                .fechaActual(String.valueOf(LocalDate.now()))
                .pais(ipInfo.getCountry_name())
                .isoCode(ipInfo.getCountry_code())
                .idiomas(formattedLanguage.toString())
                .monedas(localCurrency + " (1 " + localCurrency + " = " + monedaLocalEnUsd + "U$S)")
                .distancia(distancia + "kms (" + geoLocalization.getLongitudMeliArg().intValue() + ", "
                        + geoLocalization.getLatitudMeliArg().intValue() + ") a (" + ipInfo.getLongitude().intValue() + ", " + ipInfo.getLatitude().intValue() + ")")
                .hora(Objects.nonNull(ipInfo.getTimeZone()) ? ipInfo.getTimeZone().getCurrentTime() : String.valueOf(LocalDate.now()))
                .build();

    }

    private IpInfo devolverIpInfo(String ip) throws JsonProcessingException {
        String urlToLocalizator = propiedades.getAPI_LOCALIZATOR_URL() + ip + "?access_key=" + propiedades.getAPI_LOCALIZATOR_ACCESS_KEY();
        String ipDataFetched = dataRequest.getRequest(urlToLocalizator);

        IpInfo ipInfo = objectMapper.readValue(ipDataFetched, IpInfo.class);

        return ipInfo;
    }

    private Double devolverMonedaLocal(String localCurrency) throws JsonProcessingException {
        String url = propiedades.getAPI_EXCHANGE_URL() + "?" + ACCESS_KEY + "=" + propiedades.getAPI_EXCHANGE_ACCESS_KEY() + "&" + SIMBOLS + "=" + localCurrency;

        String jsonResponse = restTemplate.getForObject(url, String.class);

        ExchangeRateResponse response = objectMapper.readValue(jsonResponse, ExchangeRateResponse.class);

        Double tasaLocal = response.getRates().get(localCurrency);
        Double tasaUsd = response.getRates().get("USD");
        return tasaLocal / tasaUsd;
    }
}
