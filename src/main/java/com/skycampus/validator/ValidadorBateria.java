package com.skycampus.validator;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;

public class ValidadorBateria extends BaseValidator {

    @Override
    public void validate(Mision mision) {
        if (!tieneBateriaSuficiente(mision.drone())) {
            throw new IllegalArgumentException("El drone no tiene batería suficiente");
        }
        System.out.println("[ValidadorBateria] Batería suficiente");
        nextValidator(mision);
    }

    public boolean tieneBateriaSuficiente(Drone drone) {
        return drone.bateria() >= 30;
    }
    
}
