package com.skycampus.validator;

import com.skycampus.model.Drone;

public class ValidadorMision {

    public boolean tieneBateriaSuficiente(Drone drone) {
        return new ValidadorBateria().tieneBateriaSuficiente(drone);
    }

    public void validarDestino(String destino) {
        new ValidadorDestino().validarDestino(destino);
    }

    public boolean droneEstaDisponible(Drone drone) {
        if (drone == null) {
            return false;
        }
        return drone.disponible();
    }
    
}
