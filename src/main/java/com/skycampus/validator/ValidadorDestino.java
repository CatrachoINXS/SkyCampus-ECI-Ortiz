package com.skycampus.validator;

import java.util.List;

import com.skycampus.exception.DestinoInvalidoException;
import com.skycampus.model.Mision;

public class ValidadorDestino extends BaseValidator {

    private List<String> destinosValidos = List.of(
        "Bloque A", "Bloque B", "Bloque C", "Bloque D", "Biblioteca"
    );

    @Override
    public void validate(Mision mision) {
        validarDestino(mision.destino());
        System.out.println("[ValidadorDestino] Destino válido");
        nextValidator(mision);
    }

    public void validarDestino(String destino) {
        if (destino == null || !destinosValidos.contains(destino)) {
            throw new DestinoInvalidoException();
        }
    }
    
}