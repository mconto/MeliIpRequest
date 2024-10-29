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
public class Currency {
    private String code;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String plural;
    @JsonIgnore
    private String symbol;
    @JsonIgnore
    private String symbolNative;
}

