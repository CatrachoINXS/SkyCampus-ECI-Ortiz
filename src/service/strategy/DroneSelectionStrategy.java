package service.strategy;

import java.util.List;
import model.Drone;

public interface DroneSelectionStrategy {
    Drone selectDrone(List<Drone> drones);
}
