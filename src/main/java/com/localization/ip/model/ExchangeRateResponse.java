package com.localization.ip.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

        private boolean success;
        private long timestamp;
        private String base;
        private String date;
        private Map<String, Double> rates;
}
