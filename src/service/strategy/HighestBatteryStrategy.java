package service.strategy;

import java.util.Comparator;
import java.util.List;

import model.Drone;

public class HighestBatteryStrategy implements DroneSelectionStrategy {

    @Override
    public Drone selectDrone(List<Drone> drones) {
        return drones.stream()
            .max(Comparator.comparing(Drone::bateria))
            .orElse(null);
    }
    
}
