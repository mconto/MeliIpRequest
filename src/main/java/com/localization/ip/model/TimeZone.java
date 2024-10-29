package com.localization.ip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeZone {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String currentTime;
    @JsonIgnore
    private int gmtOffset;
    @JsonIgnore
    private String code;
    @JsonIgnore
    private Boolean isDaylightSaving;

}