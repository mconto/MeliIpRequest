package com.localization.ip.service.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.localization.ip.api.ApiClient;
import com.localization.ip.model.Currency;
import com.localization.ip.model.Distancias;
import com.localization.ip.model.IpInfo;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.DataRequest;
import com.localization.ip.service.OpcionesService;
import com.localization.ip.util.GeoLocalization;
import com.localization.ip.util.Propiedades;
import com.localization.ip.util.ResponseFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier("BuscarService")
@AllArgsConstructor
public class BuscarService implements OpcionesService {

    private final String BUSCAR = "BUSCAR";


    @Autowired
    ApiClient apiClient;

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
        IpInfo ipInfo = apiClient.devolverIpInfo(ip);

        if (Objects.isNull(ipInfo.getCurrency())) {
            Currency currency = new Currency();
            currency.setCode("COP");
            ipInfo.setCurrency(currency);
        }

        String localCurrency = ipInfo.getCurrency().getCode();

        List<String> formattedLanguage = new ArrayList<>();
        ipInfo.getLocation().getLanguages().forEach(language -> formattedLanguage.add(String.format("%s (%s)", language.getNativeLanguage(), language.getCode())));

        Double monedaLocalEnUsd = apiClient.devolverMonedaLocal(localCurrency);
        String formatMoney = df.format(monedaLocalEnUsd);
        monedaLocalEnUsd = Double.parseDouble(formatMoney);
        Double distancia = geoLocalization.calcularDistancia(ipInfo.getLongitude(), ipInfo.getLatitude());
        String formatDistancia = df.format(distancia);
        distancia = Double.parseDouble(formatDistancia);

        distanciasService.almacenarDistancias(Distancias.builder().distancia(distancia).pais(ipInfo.getCountry_name()).build());

        return ResponseFactory.respuestaBuilder(ipInfo, BUSCAR, localCurrency, monedaLocalEnUsd, ip, formattedLanguage, distancia);

    }


}
