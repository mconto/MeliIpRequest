package com.localization.ip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @JsonIgnore
    private int geoname_id;
    @JsonIgnore
    private String capital;

    private List<Language> languages;
    @JsonIgnore
    private String country_flag;
    @JsonIgnore
    private String country_flag_emoji;
    @JsonIgnore
    private String country_flag_emoji_unicode;
    @JsonIgnore
    private String calling_code;
    @JsonIgnore
    private boolean is_eu;

}
