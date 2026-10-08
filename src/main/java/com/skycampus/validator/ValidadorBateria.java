package com.skycampus.validator;

import java.util.logging.Logger;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;

public class ValidadorBateria extends BaseValidator {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void validate(Mision mision) {
        if (!tieneBateriaSuficiente(mision.drone())) {
            throw new IllegalArgumentException("El drone no tiene batería suficiente");
        }
        logger.info("[ValidadorBateria] Batería suficiente");
        nextValidator(mision);
    }

    public boolean tieneBateriaSuficiente(Drone drone) {
        return drone.bateria() >= 30;
    }
    
}
