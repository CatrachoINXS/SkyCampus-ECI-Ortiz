package com.skycampus.service.strategy;

import java.util.Comparator;
import java.util.List;

import com.skycampus.model.Drone;

public class HighestBatteryStrategy implements DroneSelectionStrategy {

    @Override
    public Drone selectDrone(List<Drone> drones) {
        if(drones.isEmpty()) {
            throw new IllegalArgumentException("No hay drones para seleccionar");
        }
        return drones.stream()
            .max(Comparator.comparing(Drone::bateria))
            .get();
    }
    
}
