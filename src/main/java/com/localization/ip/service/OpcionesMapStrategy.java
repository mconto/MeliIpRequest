package com.localization.ip.service;

import com.localization.ip.service.strategies.BuscarService;
import com.localization.ip.service.strategies.ComprarService;
import com.localization.ip.service.strategies.PagarService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.localization.ip.model.OperacionesConstants.*;

@Component
@AllArgsConstructor
public class OpcionesMapStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpcionesMapStrategy.class);

    private final BuscarService buscarService;
    private final ComprarService comprarService;
    private final PagarService pagarService;

    private static final Map<String, OpcionesService> opcionesServiceMap = new HashMap<>();

    @Bean
    public Map<String, OpcionesService> buildOpcionesService() {
        opcionesServiceMap.put(BUSCAR, buscarService);
        opcionesServiceMap.put(PAGAR, pagarService);
        opcionesServiceMap.put(COMPRAR, comprarService);
        return opcionesServiceMap;
    }

    public OpcionesService getOpcionesService(String opcion) {
        if (opcionesServiceMap.containsKey(opcion)) {
            return opcionesServiceMap.get(opcion);
        }
        LOGGER.error("Opción {} not reconocida", opcion);
        return null;
    }
}
