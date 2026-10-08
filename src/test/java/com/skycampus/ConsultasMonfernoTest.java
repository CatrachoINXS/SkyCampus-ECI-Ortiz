package com.skycampus;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;
import com.skycampus.service.MisionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConsultasMonfernoTest {

    private final String modelo = "DJI Mini 3";

    private final List<Drone> flota = List.of(
            new Drone("D-01", modelo, 85, true,  "Bloque A"),
            new Drone("D-02", modelo, 42, false, "Biblioteca")
    );

    @Test
    void consultasMonfernoExecution() {
        List<Mision> misiones = List.of(
                new MisionBuilder().origen("Bloque A")
                        .destino("Bloque B")
                        .drone(flota.get(0))
                        .build(),
                new MisionBuilder().origen("Bloque B")
                        .destino("Bloque C")
                        .drone(flota.get(1))
                        .build()
        );
        assertDoesNotThrow(() -> ConsultasMonferno.ejecutarConsultas(misiones));
    }
}
