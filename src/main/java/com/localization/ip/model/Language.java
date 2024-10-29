package com.localization.ip.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Language {

    private String code;
    private String name;
    @JsonProperty("native")
    private String nativeLanguage;

}
