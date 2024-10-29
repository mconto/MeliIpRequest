package com.localization.ip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfo implements Serializable {

    private String ip;
    private String type;
    @JsonIgnore
    private String continent_code;
    @JsonIgnore
    private String continent_name;
    private String country_code;
    private String country_name;
    @JsonIgnore
    private String region_code;
    @JsonIgnore
    private String region_name;
    @JsonIgnore
    private String city;
    @JsonIgnore
    private String zip;
    private Double latitude;
    private Double longitude;
    @JsonIgnore
    private String msa;
    @JsonIgnore
    private String dma;
    @JsonIgnore
    private String radius;
    @JsonIgnore
    private String ip_routing_type;
    @JsonIgnore
    private String connection_type;
    private Location location;
    private Currency currency;
    private TimeZone timeZone;

}
