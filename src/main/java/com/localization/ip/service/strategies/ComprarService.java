package com.localization.ip.service.strategies;


import com.localization.ip.model.InputRequest;
import com.localization.ip.model.ResponseModel;
import com.localization.ip.service.DataRequest;
import com.localization.ip.service.OpcionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ComprarService")
@RequiredArgsConstructor
public class ComprarService implements OpcionesService {

    private final String URL_COMPRAR = "";

    @Autowired
    DataRequest dataRequest;

    @Override
    public ResponseModel response(String inputRequest) {
        return null;
    }
}
