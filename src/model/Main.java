package model;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Drone> flota = List.of(
            new Drone("D-01", "DJI Mini 3", 85, true,  "Bloque A"),
            new Drone("D-02", "DJI Mini 3", 42, false, "Biblioteca"),
            new Drone("D-03", "DJI Mini 3", 91, true,  "Bloque C"),
            new Drone("D-04", "DJI Mini 3", 18, true,  "Bloque B"),
            new Drone("D-05", "DJI Mini 3", 67, true,  "Bloque D")
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

        System.out.println(primeraConsulta);
        System.out.println(segundaConsulta);
        System.out.println(terceraConsulta);
        System.out.println(cuartaConsulta);
    }
}
