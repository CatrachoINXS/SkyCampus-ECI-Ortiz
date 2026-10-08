package com.skycampus.service.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;

public class LessAcumulatedUsageStrategy implements DroneSelectionStrategy {

    private List<Mision> historialMisiones;

    public LessAcumulatedUsageStrategy(List<Mision> historialMisiones) {
        this.historialMisiones = historialMisiones;
    }

    @Override
    public Optional<Drone> selectDrone(List<Drone> drones, Mision mision) {
        return drones.stream()
            .filter(Drone::disponible)
            .filter(d -> d.capacidadGramos() >= mision.pesoPaqueteGramos())
            .min(Comparator.comparing(d -> historialMisiones.stream()
                .filter(m -> m.drone().equals(d)).count()));
    }
    
}
