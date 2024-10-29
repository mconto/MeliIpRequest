package com.localization.ip.persistence;

import com.localization.ip.model.Distancias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistanciasRepo extends JpaRepository<Distancias, Long> {

    Distancias save(Distancias distancias);
    Optional<Distancias> findByPais(String pais);

    Optional<Distancias> findFirstByOrderByDistanciaAsc();

    Optional<Distancias> findFirstByOrderByDistanciaDesc();

    @Query("SELECT SUM(d.distancia * d.invocaciones) / SUM(d.invocaciones) FROM Distancias d")
    Double calcularPromedioPonderadoDistancia();

}
