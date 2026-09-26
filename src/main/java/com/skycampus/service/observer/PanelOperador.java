package com.skycampus.service.observer;

import java.util.logging.Logger;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public class PanelOperador implements ObservadorDrone {
    private static final Logger logger = Logger.getLogger(PanelOperador.class.getName());

    @Override
    public void onEstadoCambiado(Drone drone, EstadoDrone nuevoEstado) {
        logger.info(String.format("[PANEL] Drone %s cambio de estado a: %s", drone.id(), nuevoEstado));
    }
}
