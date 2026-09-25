package com.skycampus;
import java.util.List;

import com.skycampus.model.Drone;
import com.skycampus.service.strategy.DroneSelectionStrategy;
import com.skycampus.service.strategy.HighestBatteryStrategy;

public class SkyCampus {
    
    private DroneSelectionStrategy strategy = new HighestBatteryStrategy();
    private static final String MODELO = "DJI Mini 3";

    List<Drone> flota = List.of(
        new Drone("D-01", MODELO, 85, true,  "Bloque A"),
        new Drone("D-02", MODELO, 42, false, "Biblioteca"),
        new Drone("D-03", MODELO, 91, true,  "Bloque C"),
        new Drone("D-04", MODELO, 18, true,  "Bloque B"),
        new Drone("D-05", MODELO, 67, true,  "Bloque D")
    );

    public Drone selectDrone() {
        return this.strategy.selectDrone(flota);
    }

    public void setDroneSelectionStrategy(DroneSelectionStrategy strategy) {
        this.strategy = strategy;
    }
}
