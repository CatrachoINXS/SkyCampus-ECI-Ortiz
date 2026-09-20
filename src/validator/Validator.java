package validator;

import model.Mision;

public interface Validator {
    Validator setNext(Validator validator);
    void validate(Mision mision);
}
