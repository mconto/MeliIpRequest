package com.localization.ip.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.localization.ip.model.ExchangeRateResponse;
import com.localization.ip.model.IpInfo;
import com.localization.ip.service.DataRequest;
import com.localization.ip.util.GeoLocalization;
import com.localization.ip.util.Propiedades;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ApiClient {

    private final String ACCESS_KEY = "access_key";
    private final String SIMBOLS = "simbols";

    @Autowired
    Propiedades propiedades;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GeoLocalization geoLocalization;

    @Autowired
    DataRequest dataRequest;

    private final RestTemplate restTemplate;

    public static IpInfo devolverIpInfo(String ip) throws JsonProcessingException {
        String urlToLocalizator = propiedades.getAPI_LOCALIZATOR_URL() + ip + "?access_key=" + propiedades.getAPI_LOCALIZATOR_ACCESS_KEY();
        String ipDataFetched = dataRequest.getRequest(urlToLocalizator);

        IpInfo ipInfo = objectMapper.readValue(ipDataFetched, IpInfo.class);

        return ipInfo;
    }

    public static Double devolverMonedaLocal(String localCurrency) throws JsonProcessingException {
        String url = propiedades.getAPI_EXCHANGE_URL() + "?" + ACCESS_KEY + "=" + propiedades.getAPI_EXCHANGE_ACCESS_KEY() + "&" + SIMBOLS + "=" + localCurrency;

        String jsonResponse = restTemplate.getForObject(url, String.class);

        ExchangeRateResponse response = objectMapper.readValue(jsonResponse, ExchangeRateResponse.class);

        Double tasaLocal = response.getRates().get(localCurrency);
        Double tasaUsd = response.getRates().get("USD");
        return tasaLocal / tasaUsd;
    }
}
