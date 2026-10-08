package com.skycampus.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    @DisplayName("Drone con batería de 30% puede ser asignado")
    void droneBateriaJusta_puedeAsignarse() {
        Drone d = new Drone("D-01", "DJI Mini 3", 30, true, "Bloque A");
        boolean resultado = v.tieneBateriaSuficiente(d);
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Drone con batería < 30% NO puede ser asignado")
    void droneBateriaCritica_noAsignable() {
        Drone d = new Drone("D-04", "DJI Mini 3", 18, true, "Bloque B");
        assertFalse(v.tieneBateriaSuficiente(d));
    }

    @Test
    @DisplayName("Destino válido no lanza excepcion")
    void destinoValido_NolanzaExcepcion() {
        assertDoesNotThrow(() -> v.validarDestino("Bloque A"));
    }

    @Test
    @DisplayName("Destino inválido lanza excepción")
    void destinoInvalido_lanzaExcepcion() {
        assertThrows(DestinoInvalidoException.class,
            () -> v.validarDestino("Edificio Inexistente"));
    }

    @Test
    @DisplayName("Destino nulo lanza excepción")
    void destinoNulo_lanzaExcepcion() {
        assertThrows(DestinoInvalidoException.class,
            () -> v.validarDestino(null));
    }

    @Test
    @DisplayName("Drone disponible puede ser asignado")
    void droneDisponible_puedeAsignarse() {
        Drone d = new Drone("D-01", "DJI Mini 3", 85, true, "Bloque A");
        assertTrue(v.droneEstaDisponible(d));
    }

    @Test
    @DisplayName("Drone ocupado no puede ser asignado")
    void droneNoDisponible_noPuedeAsignarse() {
        Drone d = new Drone("D-01", "DJI Mini 3", 85, false, "Bloque A");
        assertFalse(v.droneEstaDisponible(d));
    }

    @Test
    @DisplayName("Drone con referencia nula no puede ser asignado")
    void droneNulo_noPuedeAsignarse() {
        Drone drone = null;
        assertFalse(v.droneEstaDisponible(drone));
    }
}
