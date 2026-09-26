package com.skycampus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skycampus.model.*;
import com.skycampus.service.strategy.*;

public class GestorMisionesStrategyTest {

    private List<Drone> flota;
    private Mision mision;

    @BeforeEach
    void setUp() {

        final String modelo = "DJI Mini 3";

        flota = List.of(
            new Drone("D-01", modelo, 85, true,  "Bloque A", TipoDrone.CARGO),
            new Drone("D-02", modelo, 42, false, "Biblioteca", TipoDrone.EXPRESS),
            new Drone("D-03", modelo, 91, true,  "Bloque C", TipoDrone.MINI),
            new Drone("D-04", modelo, 18, true,  "Bloque B", TipoDrone.EXPRESS),
            new Drone("D-05", modelo, 67, true,  "Bloque D", TipoDrone.CARGO)
        );

        mision = new MisionBuilder()
            .id("M1")
            .drone(flota.get(3))
            .origen("Bloque A")
            .destino("Bloque B")
            .tipoCarga(TipoCarga.CARPETA)
            .prioridad(Prioridad.NORMAL)
            .peso(300)
            .build();
    }

    @Test 
    @DisplayName("GestorMisiones funciona con cualquier EstrategiaAsignacion sin modificar su código")
    public void gestorMisionesFuncionaConCualquierEstrategiaAsignacion() {
        DroneSelectionStrategy estrategia = new HighestBatteryStrategy();
        GestorMisiones gestor = new GestorMisiones(estrategia);

        Optional<Drone> dron1 = gestor.asignarDrone(flota, mision);
        assertEquals("D-03", dron1.get().id());

        gestor.setStrategy(new CompatibleTypeStrategy());
        Optional<Drone> dron2 = gestor.asignarDrone(flota, mision);
        assertEquals("D-01", dron2.get().id());
    }
    
}
