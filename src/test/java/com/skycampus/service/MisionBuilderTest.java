package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.TipoDrone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MisionBuilderTest {

    @Test
    void misionSinDronLanzaExcepcion() {
        MisionBuilder mision = new MisionBuilder()
                .origen("Bloque A").destino("Bloque B");
        assertThrows(IllegalStateException.class, mision::build);
    }

    @Test
    void misionSinOrigenLanzaExcepcion() {
        MisionBuilder mision = new MisionBuilder()
                .destino("Bloque B")
                .drone(new Drone("D-01", "DJI", 85, true,  "Bloque A", TipoDrone.CARGO));
        assertThrows(IllegalStateException.class, mision::build);
    }

    @Test
    void misionSinDestinoLanzaExcepcion() {
        MisionBuilder mision = new MisionBuilder()
                .origen("Bloque B")
                .drone(new Drone("D-01", "DJI", 85, true,  "Bloque A", TipoDrone.CARGO));
        assertThrows(IllegalStateException.class, mision::build);
    }
}
