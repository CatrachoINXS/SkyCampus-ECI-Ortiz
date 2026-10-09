package com.skycampus.service.strategy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.skycampus.service.composite.RutaComponent;

public class OptimizacionRapida implements OptimizacionStrategy {

    private static final Logger logger = Logger.getLogger(OptimizacionRapida.class.getName());

    @Override
    public void optimizar(RutaComponent ruta) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Optimizando ruta para MINIMIZAR TIEMPO. Distancia: " + ruta.calcularDistancia());
        }
    }
}
