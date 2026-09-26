package com.skycampus.service.observer;

import java.util.logging.Logger;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class SistemaLog implements ObservadorDrone {
    private static final Logger logger = Logger.getLogger(SistemaLog.class.getName());

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        logger.info(String.format("[AUDITORÍA LOG] Dron %s. Estado actual: %s", drone.id(), nuevoEstado));
    }
}
