package com.skycampus;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.skycampus.model.*;

public class ConsultasMonferno {

    private static final Logger logger = Logger.getLogger(ConsultasMonferno.class.getName());
    
    public static void ejecutarConsultas(List<Mision> misiones) {

        Map<TipoDrone, Long> primeraConsulta = misiones.stream()
            .filter(m -> m.estado() == EstadoMision.ENTREGADA)
            .collect(Collectors.groupingBy(m -> m.drone().tipo(), Collectors.counting()));

        Drone segundaConsulta = misiones.stream()
            .filter(m -> m.estado() == EstadoMision.ENTREGADA)
            .collect(Collectors.groupingBy(m -> m.drone(), Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(v -> v.getValue()))
            .map(d -> d.getKey())
            .get();

        double terceraConsulta = ((double) misiones.stream()
            .filter(m -> m.estado() == EstadoMision.FALLIDA)
            .count() / misiones.size()) * 100;

        boolean cuartaConsulta = misiones.stream()
            .anyMatch(m -> (m.estado() == EstadoMision.PENDIENTE && 
                m.prioridad() == Prioridad.URGENTE));

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.valueOf(primeraConsulta));
            logger.info(String.valueOf(segundaConsulta));
            logger.info(String.valueOf(terceraConsulta));
            logger.info(String.valueOf(cuartaConsulta));
        }
    }
}
