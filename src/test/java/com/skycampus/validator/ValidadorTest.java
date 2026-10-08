package com.skycampus.validator;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;
import com.skycampus.model.TipoCarga;
import com.skycampus.service.MisionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidadorTest {

    private Validator cadena;

    @BeforeEach
    void setUp() {
        cadena = new ValidadorBateria();
        cadena.setNext(new ValidadorDestino())
                .setNext(new ValidadorCarga());

    }

    @Test
    void validadorCargaLanzaExcepcionSiCargaNoEsCompatible() {
        Mision mision = new MisionBuilder().id("m-01")
                .origen("Bloque A").destino("Bloque B")
                .drone(new Drone("D-01", "DJI Mini 3", 85, true,  "Bloque A"))
                .tipoCarga(TipoCarga.LIBRO)
                .build();
        assertThrows(IllegalArgumentException.class, () -> cadena.validate(mision));
    }

    @Test
    void validadorCargaNoLanzaExcepcionSiCargaEsCompatible() {
        Mision mision = new MisionBuilder().id("m-01")
                .origen("Bloque A").destino("Bloque B")
                .drone(new Drone("D-01", "DJI Mini 3", 85, true,  "Bloque A"))
                .tipoCarga(TipoCarga.CARPETA)
                .build();
        assertDoesNotThrow(() -> cadena.validate(mision));
    }

    @Test
    void validadorCargaNoLanzaExcepcionSiDronConDiferenteModelo() {
        Mision mision = new MisionBuilder().id("m-01")
                .origen("Bloque A").destino("Bloque B")
                .drone(new Drone("D-01", "Express", 85, true,  "Bloque A"))
                .tipoCarga(TipoCarga.LIBRO)
                .build();
        assertDoesNotThrow(() -> cadena.validate(mision));
    }

    @Test
    void validadorBateriaLanzaExcepcionSiDroneNoTieneBateriaSuficiente() {
        Mision mision = new MisionBuilder().id("m-01")
                .origen("Bloque A").destino("Bloque B")
                .drone(new Drone("D-01", "DJI Mini 3", 20, true,  "Bloque A"))
                .tipoCarga(TipoCarga.CARPETA)
                .build();
        assertThrows(IllegalArgumentException.class, () -> cadena.validate(mision));
    }

    @Test
    void validadorBateriaNoLanzaExcepcionSiDroneTieneBateriaSuficiente() {
        Mision mision = new MisionBuilder().id("m-01")
                .origen("Bloque A").destino("Bloque B")
                .drone(new Drone("D-01", "DJI Mini 3", 31, true,  "Bloque A"))
                .tipoCarga(TipoCarga.CARPETA)
                .build();
        assertDoesNotThrow(() -> cadena.validate(mision));
    }
}
