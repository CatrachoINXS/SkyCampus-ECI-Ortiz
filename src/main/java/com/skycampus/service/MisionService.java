package com.skycampus.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.ServicioAerocivil;
import com.skycampus.model.ServicioClima;
import com.skycampus.model.dto.request.MisionRequestDTO;
import com.skycampus.model.dto.response.MisionResponseDTO;
import com.skycampus.model.dto.response.MisionResponseDTO.DroneResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MisionService {
 
    static final int PESO_MAXIMO_GRAMOS = 5000;
    static final Set<String> SEDES_ACTIVAS = Set.of("ECI", "UNAL");
 
    private final ServicioClima clima;
    private final ServicioAerocivil aerocivil;
    private final FlotaDrones flota;
 
    private final GestorMisiones gestorMisiones = new GestorMisiones();
    private final GestorFlota gestorFlota = new GestorFlota();
 
    public MisionResponseDTO crearMision(MisionRequestDTO misionRequest) {
        Prioridad prioridad = validar(misionRequest);
 
        if (!aerocivil.solicitarPermiso(misionRequest.getSedeOrigen() + "->" + misionRequest.getDestino())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Aerocivil rechazó el permiso de vuelo");
        }
 
        if (!clima.condicionesAptas(misionRequest.getSedeOrigen(), misionRequest.getDestino())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Condiciones climáticas no aptas");
        }
 
        Mision mision = new Mision(null, null, misionRequest.getSedeOrigen(), misionRequest.getDestino(), null, null,
                prioridad, null, null, misionRequest.getPesoPaquete(), null);
        Optional<Drone> seleccionado = gestorMisiones.asignarDrone(flota.disponibles(), mision);
        Drone drone = seleccionado.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay drones disponibles"));
 
        Drone enVuelo = gestorFlota.cambiarEstado(drone, EstadoDrone.EN_VUELO);
        return new MisionResponseDTO(enVuelo.estado().name(), new DroneResponseDTO(enVuelo.id()));
    }
 
    private Prioridad validar(MisionRequestDTO misionRequest) {
        if (misionRequest == null) {
            throw badRequest("El cuerpo de la solicitud es obligatorio");
        }
        if (!sedeActiva(misionRequest.getSedeOrigen())) {
            throw badRequest("La sede de origen no existe o está inactiva");
        }
        if (!sedeActiva(misionRequest.getDestino())) {
            throw badRequest("El destino no existe o está inactivo");
        }
        if (misionRequest.getSedeOrigen().equalsIgnoreCase(misionRequest.getDestino())) {
            throw badRequest("El origen y el destino deben ser distintos");
        }
        Integer peso = misionRequest.getPesoPaquete();
        if (peso == null || peso <= 0 || peso > PESO_MAXIMO_GRAMOS) {
            throw badRequest("El peso del paquete debe estar entre 1 y " + PESO_MAXIMO_GRAMOS + " gramos");
        }
        if (misionRequest.getPrioridad() == null || misionRequest.getPrioridad().isBlank()) {
            throw badRequest("La prioridad es obligatoria");
        }
        try {
            return Prioridad.valueOf(misionRequest.getPrioridad().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw badRequest("Prioridad inválida: " + misionRequest.getPrioridad());
        }
    }
 
    private boolean sedeActiva(String sede) {
        return sede != null && SEDES_ACTIVAS.contains(sede.trim().toUpperCase());
    }
 
    private ResponseStatusException badRequest(String msg) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }
}
 
