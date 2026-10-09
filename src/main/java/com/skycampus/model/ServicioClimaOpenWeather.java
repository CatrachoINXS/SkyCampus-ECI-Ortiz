package com.skycampus.model;

public class ServicioClimaOpenWeather implements ServicioClima {

    @Override
    public boolean condicionesAptas(String origen, String destino) {
        // HTTP aquí
        return true;
    }
    
}
