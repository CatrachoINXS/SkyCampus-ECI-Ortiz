package validator;

import model.Mision;
import model.TipoCarga;

public class ValidadorCarga extends BaseValidator {

    @Override
    public void validate(Mision mision) {

        TipoCarga tipoCarga = mision.tipoCarga();
        if (mision.drone().modelo().equals("DJI Mini 3") && (
            !tipoCarga.equals(TipoCarga.SOBRE) || !tipoCarga.equals(TipoCarga.CARPETA))) {

            throw new IllegalArgumentException("La carga supera el peso máximo");
        }
    }
    
}
