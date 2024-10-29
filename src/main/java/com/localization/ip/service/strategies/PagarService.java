package com.localization.ip.service.strategies;

import com.localization.ip.model.InputRequest;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.DataRequest;
import com.localization.ip.service.OpcionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@Qualifier("PagarService")
@RequiredArgsConstructor
public class PagarService implements OpcionesService {

    private final String URL_PAGAR = "";

    @Autowired
    DataRequest dataRequest;

    @Override
    public ResponseModel response(String inputRequest) {
        return null;
    }
}
