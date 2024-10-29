package com.localization.ip.persistence;

import com.localization.ip.model.Distancias;

public interface DistanciasI {

    Boolean almacenarDistancias(Distancias distancias);

    Distancias encontrarDistanciaLejana();

    Distancias encontrarDistanciaCercana();

    Double calcularPromedio();
}
