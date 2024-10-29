package com.localization.ip.controller;

import com.localization.ip.model.Distancias;
import com.localization.ip.service.strategies.DistanciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/ipRequest/")
public class DistanciasCtr {

    @Autowired
    DistanciasService distanciasService;

    @GetMapping("/distancia-lejana")
    public ResponseEntity<Distancias> returnDistanciaLejana() {
        Distancias distancias = null;
        try {
            distancias = distanciasService.encontrarDistanciaLejana();
            return ResponseEntity.ok(distancias);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/distancia-cercana")
    public ResponseEntity<Distancias> returnDistanciaCercana() {
        Distancias distancias = null;
        try {
            distancias = distanciasService.encontrarDistanciaCercana();
            return ResponseEntity.ok(distancias);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/promedio-distancias")
    public ResponseEntity<Double> returnPromedioDistancia() {
        Double promDistancias = null;
        try {
            promDistancias = distanciasService.calcularPromedio();
            return ResponseEntity.ok(promDistancias);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
