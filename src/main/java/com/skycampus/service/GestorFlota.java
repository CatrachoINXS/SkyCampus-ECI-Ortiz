package com.skycampus.service;

import java.util.ArrayList;
import java.util.List;

import com.skycampus.service.observer.*;
import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class GestorFlota {
    
    private final List<ObservadorDrone> obs = new ArrayList<>();
    void suscribir(ObservadorDrone o) { obs.add(o); }
    Drone cambiarEstado(Drone d, EstadoDrone nuevo) {
        Drone dronActualizado = new Drone(
            d.id(),
            d.modelo(),
            d.bateria(),
            d.disponible(),
            d.ubicacion(),
            nuevo,
            d.tipo()
        );
        obs.forEach(o -> o.onEstadoCambiado(dronActualizado, nuevo));
        return dronActualizado;
    }
}
