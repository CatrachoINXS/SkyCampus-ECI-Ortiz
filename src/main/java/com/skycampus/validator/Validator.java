package com.skycampus.validator;

import com.skycampus.model.Mision;

public interface Validator {
    Validator setNext(Validator validator);
    void validate(Mision mision);
}
