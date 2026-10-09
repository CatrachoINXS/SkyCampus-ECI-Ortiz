package com.skycampus.service.strategy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.skycampus.service.composite.RutaComponent;

public class OptimizacionBateria implements OptimizacionStrategy {

    private static final Logger logger = Logger.getLogger(OptimizacionBateria.class.getName());

    @Override
    public void optimizar(RutaComponent ruta) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Optimizando ruta para AHORRO DE BATERÍA."));
        }
    }
}
