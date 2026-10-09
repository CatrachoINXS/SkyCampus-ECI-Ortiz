package com.skycampus.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skycampus.service.composite.RutaCompuesta;
import com.skycampus.service.composite.RutaSimple;
import com.skycampus.service.observer.Observer;

public class RutaCompositeTest {
 
    private final Logger loggerRutaSimple = Logger.getLogger(RutaSimple.class.getName());
    private Level nivelOriginal;
 
    @BeforeEach
    void guardarNivel() {
        nivelOriginal = loggerRutaSimple.getLevel();
    }
 
    @AfterEach
    void restaurarNivel() {
        loggerRutaSimple.setLevel(nivelOriginal);
    }
 
    @Test
    void rutaSimple_calcularDistancia_devuelveLaDistanciaConfigurada() {
        RutaSimple ruta = new RutaSimple("Bloque A", "Bloque C", 120.5);
        assertEquals(120.5, ruta.calcularDistancia());
    }
 
    @Test
    void rutaSimple_ejecutarRuta_conLogHabilitado() {
        loggerRutaSimple.setLevel(Level.INFO);
        RutaSimple ruta = new RutaSimple("Bloque A", "Bloque C", 10);
        assertDoesNotThrow(ruta::ejecutarRuta);
    }
 
    @Test
    void rutaSimple_ejecutarRuta_sinLog() {
        loggerRutaSimple.setLevel(Level.OFF);
        RutaSimple ruta = new RutaSimple("Bloque A", "Bloque C", 10);
 
        assertDoesNotThrow(ruta::ejecutarRuta);
    }
 
    @Test
    void rutaSimple_observers_agregarNotificarYRemover() {
        RutaSimple ruta = new RutaSimple("A", "B", 5);
        Observer observer = () -> { };
 
        assertDoesNotThrow(() -> {
            ruta.agregarObserver(observer);
            ruta.notificarObservers();
            ruta.removerObserver(observer);
            ruta.notificarObservers();
        });
    }
 
    @Test
    void rutaCompuesta_sinRutas_distanciaEsCero() {
        assertEquals(0.0, new RutaCompuesta().calcularDistancia());
    }
 
    @Test
    void rutaCompuesta_calcularDistancia_sumaLasDistanciasDeSusRutas() {
        RutaCompuesta compuesta = new RutaCompuesta();
        compuesta.agregarRuta(new RutaSimple("A", "B", 100));
        compuesta.agregarRuta(new RutaSimple("B", "C", 50.5));
 
        assertEquals(150.5, compuesta.calcularDistancia(), 0.0001);
    }
 
    @Test
    void rutaCompuesta_calcularDistancia_funcionaConCompuestasAnidadas() {
        RutaCompuesta interna = new RutaCompuesta();
        interna.agregarRuta(new RutaSimple("B", "C", 30));
 
        RutaCompuesta externa = new RutaCompuesta();
        externa.agregarRuta(new RutaSimple("A", "B", 70));
        externa.agregarRuta(interna);
 
        assertEquals(100.0, externa.calcularDistancia(), 0.0001);
    }
 
    @Test
    void rutaCompuesta_ejecutarRuta_noLanzaExcepcion() {
        RutaCompuesta compuesta = new RutaCompuesta();
        compuesta.agregarRuta(new RutaSimple("A", "B", 10));
        assertDoesNotThrow(compuesta::ejecutarRuta);
    }
 
    @Test
    void rutaCompuesta_observers_agregarNotificarYRemover() {
        RutaCompuesta compuesta = new RutaCompuesta();
        compuesta.agregarRuta(new RutaSimple("A", "B", 10));
        Observer observer = () -> { };
 
        assertDoesNotThrow(() -> {
            compuesta.agregarObserver(observer);
            compuesta.notificarObservers();
            compuesta.removerObserver(observer);
            compuesta.notificarObservers();
        });
    }
}
