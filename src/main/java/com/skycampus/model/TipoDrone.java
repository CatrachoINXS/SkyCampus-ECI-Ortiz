package com.skycampus.model;

public enum TipoDrone {
    MINI("Limitada", 500, "ágil"),
    CARGO("Limitada", 2000, "lento"),
    EXPRESS("Ilimitada", 800, "rápido");

    private final String bateria;
    private final int capacidadGramos;
    private final String velocidad;

    TipoDrone(String bateria, int capacidadGramos, String velocidad) {
        this.bateria = bateria;
        this.capacidadGramos = capacidadGramos;
        this.velocidad = velocidad;
    }

    public String getBateria() {
        return bateria;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public int capacidadGramos() {
        return this.capacidadGramos;
    }
}
