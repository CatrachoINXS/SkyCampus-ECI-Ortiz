package com.skycampus.validator;

import java.util.List;
import java.util.logging.Logger;

import com.skycampus.exception.DestinoInvalidoException;
import com.skycampus.model.Mision;

public class ValidadorDestino extends BaseValidator {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private List<String> destinosValidos = List.of(
        "Bloque A", "Bloque B", "Bloque C", "Bloque D", "Biblioteca"
    );

    @Override
    public void validate(Mision mision) {
        validarDestino(mision.destino());
        logger.info("[ValidadorDestino] Destino válido");
        nextValidator(mision);
    }

    public void validarDestino(String destino) {
        if (destino == null || !destinosValidos.contains(destino)) {
            throw new DestinoInvalidoException();
        }
    }
    
}