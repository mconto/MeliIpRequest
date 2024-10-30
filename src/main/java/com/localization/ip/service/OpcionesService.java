package com.localization.ip.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.localization.ip.model.ResponseModel;

public interface OpcionesService {

    ResponseModel response(String inputRequest) throws JsonProcessingException;

}
