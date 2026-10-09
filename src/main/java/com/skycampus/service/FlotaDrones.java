package com.skycampus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skycampus.model.Drone;

@Component
public class FlotaDrones {
 
    private final List<Drone> drones = new ArrayList<>();
 
    public FlotaDrones() {
        reiniciar();
    }
 
    public List<Drone> disponibles() {
        return drones.stream().filter(Drone::disponible).toList();
    }
 
    public void vaciar() {
        drones.clear();
    }
 
    public void reiniciar() {
        drones.clear();
        drones.add(new Drone("D-01", "SkyCargo-X1", 90, true, "ECI"));
        drones.add(new Drone("D-02", "SkyCargo-X1", 70, true, "ECI"));
    }
}
 