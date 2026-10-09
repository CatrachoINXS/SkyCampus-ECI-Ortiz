package com.skycampus.service.factory;

import com.skycampus.model.Drone;

public abstract class DroneFactory {
    public abstract Drone crearDrone(String id);
}
