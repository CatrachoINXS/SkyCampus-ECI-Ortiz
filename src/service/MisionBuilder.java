package service;

import java.time.LocalTime;

import model.*;

public class MisionBuilder {

    private Drone drone;
    private String origen, destino, id;
    private TipoCarga tipoCarga;

    private EstadoMision estadoMision = EstadoMision.PENDIENTE;
    private LocalTime horaMaxima;
    private String notas = "";
    private int prioridad = 3;

    public MisionBuilder id(String id) { this.id = id; return this; }
    public MisionBuilder drone(Drone drone) { this.drone = drone; return this; }
    public MisionBuilder origen(String origen) { this.origen = origen; return this; }
    public MisionBuilder destino(String destino) { this.destino = destino; return this; }
    public MisionBuilder tipoCarga(TipoCarga carga) { this.tipoCarga = carga; return this; }

    public MisionBuilder horaMaxima(LocalTime hora) { this.horaMaxima = hora; return this; }
    public MisionBuilder notas(String nota)   { this.notas = nota;  return this; }

    public Mision build() {
        if (origen == null || destino == null || drone == null) {
            throw new IllegalStateException("Drone, origen y destino son obligatorios");
        }
        return new Mision(id, drone, origen, destino, tipoCarga, estadoMision, prioridad, notas, horaMaxima);
    }

}

// ———————— Uso ———————————————————————————————————————————————————————

    /*
     * Mision m = new MisionBuilder()
     *      .drone(d03).origen("Bloque C").destino("Biblioteca")
     *      .tipoCarga(TipoCarga.SOBRE)
     *      .horaMaxima(LocalTime.of(6, 20))
     *      .notas("Urgente Examen mañana")
     *      .build();
     */