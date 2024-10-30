package com.localization.ip.service.strategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.localization.ip.api.ApiClient;
import com.localization.ip.model.Currency;
import com.localization.ip.model.Distancias;
import com.localization.ip.model.IpInfo;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.OpcionesService;
import com.localization.ip.util.GeoLocalization;
import com.localization.ip.util.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier("PagarService")
@RequiredArgsConstructor
public class PagarService implements OpcionesService {

    private final String PAGAR = "PAGAR";


    @Autowired
    DistanciasService distanciasService;

    @Autowired
    ApiClient apiClient;

    @Autowired
    GeoLocalization geoLocalization;

    @Override
    public ResponseModel response(String ip) throws JsonProcessingException {
        DecimalFormat df = new DecimalFormat("#,###");
        IpInfo ipInfo = apiClient.devolverIpInfo(ip);

        if (Objects.isNull(ipInfo.getCurrency())){
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

        return ResponseFactory.respuestaBuilder(ipInfo, PAGAR, localCurrency, monedaLocalEnUsd, ip, formattedLanguage, distancia);

    }
}
