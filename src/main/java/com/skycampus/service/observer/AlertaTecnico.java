package com.skycampus.service.observer;

import java.util.logging.Logger;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class AlertaTecnico implements ObservadorDrone {
    private static final Logger logger = Logger.getLogger(AlertaTecnico.class.getName());

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        if (nuevoEstado == EstadoDrone.FALLO) {
            logger.warning(String.format("[ALERTA TÉCNICA] El dron %s entró en estado FALLO.", drone.id()));
        }
    }
}
