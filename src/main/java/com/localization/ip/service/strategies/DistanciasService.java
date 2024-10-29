package com.localization.ip.service.strategies;

import com.localization.ip.model.Distancias;
import com.localization.ip.persistence.DistanciasI;
import com.localization.ip.persistence.DistanciasRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DistanciasService implements DistanciasI {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistanciasService.class);

    DistanciasRepo distanciasRepo;

    @Override
    public Boolean almacenarDistancias(Distancias distancias) {
        Distancias distanciaExistente = distanciasRepo.findByPais(distancias.getPais()).orElse(null);
        if (Objects.isNull(distanciaExistente)) {
            distanciasRepo.save(distancias);
            return true;
        } else {
            try {
                distanciaExistente.setInvocaciones(distanciaExistente.getInvocaciones() + 1);
                distanciasRepo.save(distanciaExistente);
                return true;
            } catch (OptimisticLockingFailureException e) {
                LOGGER.error("Error persistiendo: {}", e.getMessage(), e);
                return false;
            }
        }
    }

    @Override
    public Distancias encontrarDistanciaLejana() {
        return distanciasRepo.findFirstByOrderByDistanciaAsc().isPresent()
                ?distanciasRepo.findFirstByOrderByDistanciaAsc().get()
                :Distancias.builder().build();
    }

    @Override
    public Distancias encontrarDistanciaCercana() {
        return distanciasRepo.findFirstByOrderByDistanciaDesc().isPresent()
                ?distanciasRepo.findFirstByOrderByDistanciaDesc().get()
                :Distancias.builder().build();
    }

    @Override
    public Double calcularPromedio() {
        return distanciasRepo.calcularPromedioPonderadoDistancia();
    }

}
