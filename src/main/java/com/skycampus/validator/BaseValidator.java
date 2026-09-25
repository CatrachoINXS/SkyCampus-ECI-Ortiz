package com.skycampus.validator;

import com.skycampus.model.Mision;

public abstract class BaseValidator implements Validator {
    
    private Validator next;

    @Override 
    public Validator setNext(Validator validator) {
        this.next = validator;
        return validator;
    }

    protected void nextValidator(Mision mision) {
        if (next != null) {
            this.next.validate(mision);
        }
    }

}