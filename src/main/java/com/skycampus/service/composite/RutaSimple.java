package com.skycampus.service.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.skycampus.service.observer.Observer;
import com.skycampus.service.observer.Subject;

public class RutaSimple implements RutaComponent, Subject {

    private final String origen;
    private final String destino;
    private final double distancia;

    private static final Logger logger = Logger.getLogger(RutaSimple.class.getName());
    private List<Observer> observadores = new ArrayList<>();

    public RutaSimple(String origen, String destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    @Override
    public double calcularDistancia() {
       return distancia;
    }

    @Override
    public void ejecutarRuta() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Ejecutando ruta simple: " + origen + " - " + destino);
        }
    }

    @Override
    public void agregarObserver(Observer observer) {
        observadores.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservers() {
        observadores.forEach(Observer::notificarAlertaRuta);
    }
    
}
