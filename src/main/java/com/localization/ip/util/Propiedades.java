package com.localization.ip.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propiedades {

    @Value("${api-exchange.access-key}")
    private String API_EXCHANGE_ACCESS_KEY;

    @Value("${api-exchange.url}")
    private String API_EXCHANGE_URL;

    @Value("${api-localizator.access-key}")
    private String API_LOCALIZATOR_ACCESS_KEY;

    @Value("${api-localizator.url}")
    private String API_LOCALIZATOR_URL;

}
