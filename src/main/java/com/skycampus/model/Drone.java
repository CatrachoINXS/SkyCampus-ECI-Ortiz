package com.skycampus.model;

public record Drone (
    String  id,
    String  modelo,
    int     bateria,
    boolean disponible,
    String  ubicacion,
    EstadoDrone estado,
    TipoDrone tipo
) {

    public Drone(String id, String modelo, int bateria, boolean disponible, String ubicacion) {
        this(id, modelo, bateria, disponible, ubicacion, EstadoDrone.DISPONIBLE, TipoDrone.CARGO);
    }
}