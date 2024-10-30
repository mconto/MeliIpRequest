package com.localization.ip.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.OpcionesMapStrategy;
import com.localization.ip.service.OpcionesService;
import com.localization.ip.util.ValidateIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RequestMapping("/ipRequest/")
public class RequestCtr {

    @Autowired
    OpcionesMapStrategy opcionesMapStrategy;

    @GetMapping("/operacion/{operacion}")
    public ResponseEntity<ResponseModel> returnGetData(@RequestHeader(value = "Forwarded", required = false) String forwardedHeader,
                                                       @PathVariable("operacion") String operacion) throws JsonProcessingException {
        if (!ValidateIp.esIpValida(forwardedHeader)){
            return ResponseEntity.badRequest().body(ResponseModel.builder().build());
        }
        OpcionesService opcionesService = opcionesMapStrategy.getOpcionesService(operacion);

        return ResponseEntity.ok(opcionesService.response(forwardedHeader));
    }
}
