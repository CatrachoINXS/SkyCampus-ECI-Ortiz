package com.skycampus.service.observer;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;

public interface ObservadorDrone {
    void onEstadoCambiado(Drone drone, EstadoDrone nuevo);
}
