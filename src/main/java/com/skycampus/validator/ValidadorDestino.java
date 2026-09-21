package com.skycampus.validator;

import java.util.List;

import com.skycampus.model.Mision;

public class ValidadorDestino extends BaseValidator {

    private List<String> destinosValidos = List.of(
        "Bloque A", "Bloque B", "Bloque C", "Bloque D", "Biblioteca"
    );

    @Override
    public void validate(Mision mision) {
        if (!destinosValidos.contains(mision.destino())) {
            throw new IllegalArgumentException("El destino no es válido");
        }
        System.out.println("[ValidadorDestino] Destino válido");
        nextValidator(mision);
    }
    
}