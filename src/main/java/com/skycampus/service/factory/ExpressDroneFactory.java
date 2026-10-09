package com.skycampus.service.factory;

import com.skycampus.model.Drone;
import com.skycampus.model.TipoDrone;

public class ExpressDroneFactory extends DroneFactory {

    @Override
    public Drone crearDrone(String id) {
        return new Drone(id, "Modelo Express", 100, true, "Bloque A", TipoDrone.EXPRESS);
    }
    
}
