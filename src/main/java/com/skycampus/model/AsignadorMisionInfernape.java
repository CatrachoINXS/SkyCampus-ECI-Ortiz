package com.skycampus.model;

import java.util.List;
import java.util.Optional;

import com.skycampus.repository.RepositorioFlota;
import com.skycampus.service.GestorFlota;
import com.skycampus.service.GestorMisiones;
import com.skycampus.service.observer.ObservadorDrone;
import com.skycampus.service.strategy.DroneSelectionStrategy;

public class AsignadorMisionInfernape {
    
    private final RepositorioFlota repo;
    private final ServicioClima clima;
    private final DroneSelectionStrategy estrategia;
    private final ObservadorDrone notificador;

    private GestorMisiones gestorMisiones = new GestorMisiones();
    private GestorFlota gestorFlota = new GestorFlota();

    public AsignadorMisionInfernape(
            RepositorioFlota r, ServicioClima c,
            DroneSelectionStrategy e, ObservadorDrone n) {
        this.repo = r; this.clima = c;
        this.estrategia = e; this.notificador = n;
    }

    public void prueba() {
        repo.findDisponibles("ECI");
        clima.condicionesAptas("Bloque A", "Bloque C");
        estrategia.selectDrone(null, null);
        notificador.onEstadoCambiado(null, null);
    }

    public Optional<Drone> asignarDrone(List<Drone> flota, Mision mision) {
        if (!clima.condicionesAptas(mision.origen(), mision.destino())) {
            return Optional.empty();
        }
        Optional<Drone> seleccionado = gestorMisiones.asignarDrone(flota, mision);
        return seleccionado.map(drone -> gestorFlota.cambiarEstado(drone, EstadoDrone.EN_VUELO));
    }
}
