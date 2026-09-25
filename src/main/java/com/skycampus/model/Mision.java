package com.skycampus.model;

import java.time.LocalTime;

public record Mision (
    String id, Drone drone, String origen, String destino, TipoCarga tipoCarga,
    EstadoMision estado, int prioridad, String notas, LocalTime horaMaxima
) {}
