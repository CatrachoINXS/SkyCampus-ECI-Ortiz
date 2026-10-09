package com.skycampus.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.service.observer.AlertaTecnico;
import com.skycampus.service.observer.MonitorObserver;
import com.skycampus.service.observer.PanelOperador;
import com.skycampus.service.observer.SistemaLog;

public class ObservadoresTest {
 
    private final Logger logAlerta = Logger.getLogger(AlertaTecnico.class.getName());
    private final Logger logPanel = Logger.getLogger(PanelOperador.class.getName());
    private final Logger logSistema = Logger.getLogger(SistemaLog.class.getName());
    private final Logger logMonitor = Logger.getLogger(MonitorObserver.class.getName());
 
    private Level nivelAlerta;
    private Level nivelPanel;
    private Level nivelSistema;
    private Level nivelMonitor;
 
    private final Drone drone = new Drone("D-01", "Modelo", 80, true, "Bloque A");
 
    @BeforeEach
    void guardarLevels() {
        nivelAlerta = logAlerta.getLevel();
        nivelPanel = logPanel.getLevel();
        nivelSistema = logSistema.getLevel();
        nivelMonitor = logMonitor.getLevel();
    }
 
    @AfterEach
    void restaurarLevels() {
        logAlerta.setLevel(nivelAlerta);
        logPanel.setLevel(nivelPanel);
        logSistema.setLevel(nivelSistema);
        logMonitor.setLevel(nivelMonitor);
    }
 
    @Test
    void alertaTecnico_estadoFallo_conLogHabilitado() {
        logAlerta.setLevel(Level.INFO);
 
        assertDoesNotThrow(() -> new AlertaTecnico().onEstadoCambiado(drone, EstadoDrone.FALLO));
    }
 
    @Test
    void alertaTecnico_estadoFallo_conLogDeshabilitado() {
        logAlerta.setLevel(Level.OFF);
 
        assertDoesNotThrow(() -> new AlertaTecnico().onEstadoCambiado(drone, EstadoDrone.FALLO));
    }
 
    @Test
    void alertaTecnico_estadoDistintoDeFallo_noAlerta() {
        logAlerta.setLevel(Level.INFO);
 
        assertDoesNotThrow(() -> new AlertaTecnico().onEstadoCambiado(drone, EstadoDrone.EN_VUELO));
    }
 
    @Test
    void panelOperador_conLogHabilitado() {
        logPanel.setLevel(Level.INFO);
 
        assertDoesNotThrow(() -> new PanelOperador().onEstadoCambiado(drone, EstadoDrone.EN_VUELO));
    }
 
    @Test
    void panelOperador_conLogDeshabilitado() {
        logPanel.setLevel(Level.OFF);
 
        assertDoesNotThrow(() -> new PanelOperador().onEstadoCambiado(drone, EstadoDrone.EN_VUELO));
    }
 
    @Test
    void sistemaLog_conLogHabilitado() {
        logSistema.setLevel(Level.INFO);
 
        assertDoesNotThrow(() -> new SistemaLog().onEstadoCambiado(drone, EstadoDrone.DISPONIBLE));
    }
 
    @Test
    void sistemaLog_conLogDeshabilitado() {
        logSistema.setLevel(Level.OFF);
 
        assertDoesNotThrow(() -> new SistemaLog().onEstadoCambiado(drone, EstadoDrone.DISPONIBLE));
    }
 
    @Test
    void monitorObserver_conLogHabilitado() {
        logMonitor.setLevel(Level.INFO);
 
        assertDoesNotThrow(() -> new MonitorObserver().notificarAlertaRuta());
    }
 
    @Test
    void monitorObserver_conLogDeshabilitado() {
        logMonitor.setLevel(Level.OFF);
 
        assertDoesNotThrow(() -> new MonitorObserver().notificarAlertaRuta());
    }
}