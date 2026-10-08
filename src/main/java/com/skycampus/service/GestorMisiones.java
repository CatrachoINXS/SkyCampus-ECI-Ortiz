package com.skycampus.service;

import java.util.List;
import java.util.Optional;

import com.skycampus.model.Prioridad;
import com.skycampus.service.strategy.*;
import com.skycampus.model.Drone;
import com.skycampus.model.Mision;

public class GestorMisiones {
    
    private DroneSelectionStrategy strategy;

    public GestorMisiones() {
        this.strategy = new HighestBatteryStrategy();
    }

    public void setStrategy(DroneSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public Optional<Drone> asignarDrone(List<Drone> flota, Mision mision) {
        if (!mision.prioridad().equals(Prioridad.URGENTE)) {
            setStrategy(new HighestBatteryStrategy());
        }
        return strategy.selectDrone(flota, mision);
    }

}
