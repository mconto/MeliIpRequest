package com.localization.ip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {

    @JsonProperty("Operacion a realizar")
    private String operacion;
    @JsonProperty("IP")
    private String ip;
    @JsonProperty("Fecha actual")
    private String fechaActual;
    @JsonProperty("País")
    private String pais;
    @JsonProperty("ISO Code")
    private String isoCode;
    @JsonProperty("Idiomas")
    private String idiomas;
    @JsonProperty("Monedas")
    private String monedas;
    @JsonProperty("Hora")
    private String hora;
    @JsonProperty("Distancia estimada")
    private String distancia;
}
