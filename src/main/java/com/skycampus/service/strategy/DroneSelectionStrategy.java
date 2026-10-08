package com.skycampus.service.strategy;

import java.util.List;
import java.util.Optional;

import com.skycampus.model.*;

public interface DroneSelectionStrategy {
    Optional<Drone> selectDrone(List<Drone> drones, Mision mision);
}
