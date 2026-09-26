package com.skycampus.model;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        final String modelo = "DJI Mini 3";

        List<Drone> flota = List.of(
            new Drone("D-01", modelo, 85, true,  "Bloque A"),
            new Drone("D-02", modelo, 42, false, "Biblioteca"),
            new Drone("D-03", modelo, 91, true,  "Bloque C"),
            new Drone("D-04", modelo, 18, true,  "Bloque B"),
            new Drone("D-05", modelo, 67, true,  "Bloque D")
        );

        List<String> primeraConsulta = flota.stream()
            .filter(d -> d.bateria() >= 50 && d.disponible())
            .sorted(Comparator.comparing(Drone::bateria).reversed())
            .map(Drone::id)
            .toList();

        boolean segundaConsulta = flota.stream()
            .anyMatch(p -> p.ubicacion().equals("Bloque C") && p.disponible());

        long terceraConsulta = flota.stream()
            .filter(p -> p.bateria() < 20)
            .count();

        List<String> cuartaConsulta = flota.stream()
            .map(d -> d.id() + ": " + d.bateria() + "%")
            .toList();

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.valueOf(primeraConsulta));
            logger.info(String.valueOf(segundaConsulta));
            logger.info(String.valueOf(terceraConsulta));
            logger.info(String.valueOf(cuartaConsulta));
        }
    }
}
