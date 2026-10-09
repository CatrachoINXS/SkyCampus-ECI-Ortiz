package com.skycampus.service;

import com.skycampus.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DashboardService {
    public Map<Sede, Double> calcularTasaExitoPorSede(List<Mision> misiones, List<Sede> sedes) {
        return sedes.stream().collect(Collectors.toMap(
                sede -> sede,
                sede -> {
                    List<Mision> misionesSede = misiones.stream()
                            .filter(m -> m.sede() != null && m.sede().equals(sede))
                            .toList();
                    if (misionesSede.isEmpty()) return 0.0;
                    long entregadas = misionesSede.stream()
                            .filter(m -> m.estado() == EstadoMision.ENTREGADA)
                            .count();
                    return (double) entregadas / misionesSede.size();
                }
        ));
    }

    public Map<Sede, Double> calcularTiempoPromedioPorSede(List<Mision> misiones, List<Sede> sedes) {
        return sedes.stream().collect(Collectors.toMap(
                sede -> sede,
                sede -> misiones.stream()
                        .filter(m -> m.sede() != null && m.sede().equals(sede) && m.estado() == EstadoMision.ENTREGADA)
                        .mapToDouble(m -> Mision.TIEMPO_ENTREGA_MINUTOS)
                        .average()
                        .orElse(0.0)
        ));
    }

    public Map<Sede, Double> calcularPorcentajeUrgentesPorSede(List<Mision> misiones, List<Sede> sedes) {
        return sedes.stream().collect(Collectors.toMap(
                sede -> sede,
                sede -> {
                    List<Mision> misionesSede = misiones.stream()
                            .filter(m -> m.sede() != null && m.sede().equals(sede))
                            .toList();
                    if (misionesSede.isEmpty()) return 0.0;
                    long urgentes = misionesSede.stream()
                            .filter(m -> m.prioridad() == Prioridad.URGENTE)
                            .count();
                    return (double) urgentes / misionesSede.size();
                }
        ));
    }

    public Map<Sede, Optional<Drone>> calcularDroneMasUtilizadoPorSede(List<Mision> misiones, List<Sede> sedes) {
        return sedes.stream().collect(Collectors.toMap(
                sede -> sede,
                sede -> misiones.stream()
                        .filter(m -> m.sede() != null && m.sede().equals(sede) && m.drone() != null)
                        .collect(Collectors.groupingBy(Mision::drone, Collectors.counting()))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
        ));
    }
}
