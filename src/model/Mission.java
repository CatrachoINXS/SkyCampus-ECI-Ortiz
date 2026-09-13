package model;

public record Mission(
    String       id,
    Drone        drone,
    String       origen,
    String       destino,
    TipoCarga    tipoCarga,
    EstadoMision estado
) {}
