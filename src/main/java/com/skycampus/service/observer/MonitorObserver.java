package com.skycampus.service.observer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorObserver implements Observer {

    private static final Logger logger = Logger.getLogger(MonitorObserver.class.getName());

    @Override
    public void notificarAlertaRuta() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Monitor Observer notificado de ruta");
        }
    }
    
}
