package com.skycampus.model;

import com.skycampus.repository.RepositorioFlota;
import com.skycampus.service.observer.ObservadorDrone;
import com.skycampus.service.strategy.DroneSelectionStrategy;

public class AsignadorMisionInfernape {
    
    private final RepositorioFlota   repo;
    private final ServicioClima      clima;
    private final DroneSelectionStrategy estrategia;
    private final ObservadorDrone    notificador;

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
}
