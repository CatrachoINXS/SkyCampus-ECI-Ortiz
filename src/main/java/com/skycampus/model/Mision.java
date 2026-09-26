package com.skycampus.model;

import java.time.LocalTime;

public record Mision (
    String id, Drone drone, String origen, String destino, TipoCarga tipoCarga,
    EstadoMision estado, Prioridad prioridad, String notas, LocalTime horaMaxima,
    int pesoPaqueteGramos
) {

    public Mision(String id, Drone drone, String origen, String destino, TipoCarga tipoCarga,
    EstadoMision estado, Prioridad prioridad, String notas, LocalTime horaMaxima) {
        this(id, drone, origen, destino, tipoCarga, estado, prioridad, notas, horaMaxima, 0);
    }
}
