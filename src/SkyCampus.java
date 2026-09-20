import java.util.List;

import model.Drone;
import service.strategy.DroneSelectionStrategy;
import service.strategy.HighestBatteryStrategy;

public class SkyCampus {
    
    private DroneSelectionStrategy strategy = new HighestBatteryStrategy();

    List<Drone> flota = List.of(
        new Drone("D-01", "DJI Mini 3", 85, true,  "Bloque A"),
        new Drone("D-02", "DJI Mini 3", 42, false, "Biblioteca"),
        new Drone("D-03", "DJI Mini 3", 91, true,  "Bloque C"),
        new Drone("D-04", "DJI Mini 3", 18, true,  "Bloque B"),
        new Drone("D-05", "DJI Mini 3", 67, true,  "Bloque D")
    );

    public Drone selectDrone() {
        return this.strategy.selectDrone(flota);
    }

    public void setDroneSelectionStrategy(DroneSelectionStrategy strategy) {
        this.strategy = strategy;
    }
}

// ———————— Uso ———————————————————————————————————————————————————————

    /*
     * SkyCampus service = new SkyCampus();
     * service.setDroneSelectionStrategy(new HighestBatteryStrategy());
     * 
     * Drone dron = service.selectDrone();
     */
