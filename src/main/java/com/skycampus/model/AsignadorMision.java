package com.skycampus.model;

import com.skycampus.repository.RepositorioFlota;
import com.skycampus.service.ApiMeteorologica;
import com.skycampus.service.GestorFlota;
import com.skycampus.service.GestorMisiones;
import com.skycampus.service.observer.ObservadorDrone;
import com.skycampus.service.strategy.DroneSelectionStrategy;
import com.skycampus.service.strategy.HighestBatteryStrategy;

import java.util.List;
import java.util.Optional;

public class AsignadorMision {

    private final GestorFlota gestorFlota;
    private final GestorMisiones gestorMisiones;
    private final ApiMeteorologica clima;

    public AsignadorMision(GestorFlota gestorFlota, GestorMisiones gestorMisiones, ApiMeteorologica clima) {
        this.gestorFlota = gestorFlota;
        this.gestorMisiones = gestorMisiones;
        this.clima = clima;
    }

    public Optional<Drone> asignarDrone(List<Drone> flota, Mision mision) {
        if (!clima.esApto()) {
            return Optional.empty();
        }

        if (mision.prioridad().equals(Prioridad.URGENTE)) {
            gestorMisiones.setStrategy(new HighestBatteryStrategy());
        }

        Optional<Drone> seleccionado = gestorMisiones.asignarDrone(flota, mision);

        return seleccionado.map(drone -> gestorFlota.cambiarEstado(drone, EstadoDrone.EN_VUELO));
    }
    
    public void asignarMision(Drone dron, Mision mision) {
        // Juan implementa la asignacion automática
        // Maria implementa las alertas
    }
}
