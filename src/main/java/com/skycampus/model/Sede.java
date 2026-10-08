package com.skycampus.model;


public class Sede {
    private String nombre;
    private Integer misionesCompletadas;
    private Integer misionesSolicitadas;

    public Sede(String nombre, Integer misionesCompletadas) {
        this.nombre = nombre;
        this.misionesCompletadas = misionesCompletadas;
    }

    public Integer misionesCompletadasHoy() {
        return this.misionesCompletadas;
    }

    public double tasaExito() {
        return (double) misionesCompletadas /misionesSolicitadas;
    }
}
