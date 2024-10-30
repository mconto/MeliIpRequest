package com.localization.ip.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GeoLocalization {

    private final Double longitudMeliArg = -58.37723;
    private final Double latitudMeliArg = -34.61315;

    public Double calcularDistancia(Double longitudIp, Double latitudIp) {

        return haversine(latitudMeliArg, longitudMeliArg, latitudIp, longitudIp);

    }

    public static Double haversine(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371;

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}

