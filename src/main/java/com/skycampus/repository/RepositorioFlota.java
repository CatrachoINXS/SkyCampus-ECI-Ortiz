package com.skycampus.repository;

import java.util.List;

import com.skycampus.model.Drone;

public interface RepositorioFlota {
    List<Drone> findDisponibles(String sede);
}
