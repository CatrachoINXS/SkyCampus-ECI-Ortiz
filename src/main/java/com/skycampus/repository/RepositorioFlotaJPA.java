package com.skycampus.repository;

import java.util.List;

import com.skycampus.model.Drone;

public class RepositorioFlotaJPA implements RepositorioFlota {

    @Override
    public List<Drone> findDisponibles(String sede) {
        //aqui está el JPA
        return List.of();
    }
    
}
