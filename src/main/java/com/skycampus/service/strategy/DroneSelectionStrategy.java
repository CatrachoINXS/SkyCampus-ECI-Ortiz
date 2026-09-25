package com.skycampus.service.strategy;

import java.util.List;

import com.skycampus.model.Drone;

public interface DroneSelectionStrategy {
    Drone selectDrone(List<Drone> drones);
}
