package com.skycampus.service.composite;

import java.util.ArrayList;
import java.util.List;

import com.skycampus.service.observer.Observer;
import com.skycampus.service.observer.Subject;

public class RutaCompuesta implements RutaComponent, Subject {

    private final List<RutaComponent> rutas = new ArrayList<>();
    private List<Observer> observadores = new ArrayList<>();

    public void agregarRuta(RutaComponent ruta) {
        rutas.add(ruta);
    }

    @Override
    public double calcularDistancia() {
       return rutas.stream()
            .mapToDouble(RutaComponent::calcularDistancia)
            .sum();
    }

    @Override
    public void ejecutarRuta() {
        rutas.stream().peek(RutaComponent::ejecutarRuta);
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
        observadores.stream().peek(Observer::notificarAlertaRuta);
    }
}
