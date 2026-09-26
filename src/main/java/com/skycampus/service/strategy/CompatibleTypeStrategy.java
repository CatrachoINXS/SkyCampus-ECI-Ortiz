package com.skycampus.service.strategy;

import java.util.List;
import java.util.Optional;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;

public class CompatibleTypeStrategy implements DroneSelectionStrategy {

    @Override
    public Optional<Drone> selectDrone(List<Drone> drones, Mision mision) {
        return drones.stream()
            .filter(Drone::disponible)
            .filter(d -> d.tipoCompatible(mision))
            .findFirst();
    }
    
}
