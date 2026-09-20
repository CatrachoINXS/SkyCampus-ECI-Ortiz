package service;

import java.time.LocalTime;

import model.*;

public class MisionBuilder {

    private Drone drone;
    private String origen, destino;
    private TipoCarga tipoCarga;

    private EstadoMision estadoMision = EstadoMision.PENDIENTE;
    private LocalTime horaMaxima;
    private String notas = "";
    private int prioridad = 3;

    public MisionBuilder drone(Drone d) { this.drone = d; return this; }
    public MisionBuilder origen(String o) { this.origen = o; return this; }
    public MisionBuilder destino(String d) { this.destino = d; return this; }
    public MisionBuilder tipoCarga(TipoCarga c) { this.tipoCarga = c; return this; }

    public MisionBuilder horaMaxima(LocalTime h) { this.horaMaxima = h; return this; }
    public MisionBuilder notas(String n)   { this.notas   = n;  return this; }

    public Mision build() {
        if (origen == null || destino == null || drone == null) {
            throw new IllegalStateException("Drone, origen y destino son obligatorios");
        }
        return new Mision(destino, drone, origen, destino, tipoCarga, estadoMision, prioridad, notas, horaMaxima);
    }

}

// ———————— Uso ———————————————————————————————————————————————————————

    /*
     * Mision m = new MissionBuilder()
     *      .drone(d03).origen("Bloque C").destino("Biblioteca")
     *      .tipoCarga(TipoCarga.SOBRE)
     *      .horaMaxima(LocalDate.of(6, 20))
     *      .notas("Urgente Examen mañana")
     *      .build();
     */