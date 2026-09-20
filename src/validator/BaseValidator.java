package validator;

import model.Mision;

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

// ———————— Uso ———————————————————————————————————————————————————————

    /*
     * Validator chain = new ValidadorBateria();
     * chain.setNext(new ValidadorDestino())
     *      .setNext(new ValidadorCarga());
     * 
     * chain.validate(mision);
     */
