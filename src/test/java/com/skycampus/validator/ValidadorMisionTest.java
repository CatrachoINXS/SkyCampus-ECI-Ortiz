package com.skycampus.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skycampus.model.Drone;
import com.skycampus.exception.*;

public class ValidadorMisionTest {
    private ValidadorMision v;

    @BeforeEach
    void setUp() { v = new ValidadorMision(); }

    @Test
    @DisplayName("Drone con batería ≥ 30% puede ser asignado")
    void droneBateriaSuficiente_puedeAsignarse() {
        // ARRANGE
        Drone d = new Drone("D-01", "DJI Mini 3", 85, true, "Bloque A");
        // ACT
        boolean resultado = v.tieneBateriaSuficiente(d);
        // ASSERT
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Drone con batería < 30% NO puede ser asignado")
    void droneBateriaCritica_noAsignable() {
        Drone d = new Drone("D-04", "DJI Mini 3", 18, true, "Bloque B");
        assertFalse(v.tieneBateriaSuficiente(d));
    }

    @Test
    @DisplayName("Destino inválido lanza excepción")
    void destinoInvalido_lanzaExcepcion() {
        assertThrows(DestinoInvalidoException.class,
            () -> v.validarDestino("Edificio Inexistente"));
    }
}
