package validator;

import model.Mision;

public class ValidadorBateria extends BaseValidator {

    @Override
    public void validate(Mision mision) {
        if (mision.drone().bateria() < 30) {
            throw new IllegalArgumentException("El drone no tiene batería suficiente");
        }
        System.out.println("[ValidadorBateria] Batería suficiente");
        nextValidator(mision);
    }
    
}
