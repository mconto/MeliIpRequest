package com.localization.ip.util;

import com.localization.ip.model.IpInfo;
import com.localization.ip.model.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFactory {


    public static ResponseModel respuestaBuilder(IpInfo ipInfo, String operacion, String localCurrency, Double monedaLocalEnUsd, String ip, List<String> formattedLanguage, Double distancia){
        GeoLocalization geoLocalization = new GeoLocalization();

        return ResponseModel
                .builder()
                .operacion(operacion)
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
}
