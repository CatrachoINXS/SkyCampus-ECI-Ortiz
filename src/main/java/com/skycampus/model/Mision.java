package com.skycampus.model;

import java.time.LocalTime;

public record Mision (
    String id, Drone drone, String origen, String destino, TipoCarga tipoCarga,
    EstadoMision estado, Prioridad prioridad, String notas, LocalTime horaMaxima,
    int pesoPaqueteGramos, Sede sede
) {

    public static final double TIEMPO_ENTREGA_MINUTOS = 60.0;

    public Mision(String id, Drone drone, String origen, String destino, TipoCarga tipoCarga,
    EstadoMision estado, Prioridad prioridad, String notas, LocalTime horaMaxima) {
        this(id, drone, origen, destino, tipoCarga, estado, prioridad, notas, horaMaxima, 0, null);
    }
}
