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

    public Drone(String id, String modelo, int bateria, boolean disponible, String ubicacion, TipoDrone tipo) {
        this(id, modelo, bateria, disponible, ubicacion, EstadoDrone.DISPONIBLE, tipo);
    }

    public int capacidadGramos() {
        return this.tipo.capacidadGramos();
    }

    public boolean tipoCompatible(Mision mision) {
        return capacidadGramos() >= mision.pesoPaqueteGramos();
    }
}