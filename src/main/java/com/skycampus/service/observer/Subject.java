package com.skycampus.service.observer;

public interface Subject {
    void agregarObserver(Observer observer);
    void removerObserver(Observer observer);
    void notificarObservers();
}
