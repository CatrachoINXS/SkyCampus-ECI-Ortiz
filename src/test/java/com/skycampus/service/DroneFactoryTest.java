package com.skycampus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.TipoDrone;
import com.skycampus.service.factory.CargoDroneFactory;
import com.skycampus.service.factory.DroneFactory;
import com.skycampus.service.factory.ExpressDroneFactory;

public class DroneFactoryTest {
 
    @Test
    void cargoDroneFactory_creaDroneTipoCargo() {
        DroneFactory factory = new CargoDroneFactory();
 
        Drone drone = factory.crearDrone("C-01");
 
        assertEquals("C-01", drone.id());
        assertEquals("Modelo Cargo", drone.modelo());
        assertEquals(100, drone.bateria());
        assertTrue(drone.disponible());
        assertEquals("Bloque A", drone.ubicacion());
        assertEquals(EstadoDrone.DISPONIBLE, drone.estado());
        assertEquals(TipoDrone.CARGO, drone.tipo());
    }
 
    @Test
    void expressDroneFactory_creaDroneTipoExpress() {
        DroneFactory factory = new ExpressDroneFactory();
 
        Drone drone = factory.crearDrone("E-01");
 
        assertEquals("E-01", drone.id());
        assertEquals("Modelo Express", drone.modelo());
        assertEquals(100, drone.bateria());
        assertTrue(drone.disponible());
        assertEquals("Bloque A", drone.ubicacion());
        assertEquals(EstadoDrone.DISPONIBLE, drone.estado());
        assertEquals(TipoDrone.EXPRESS, drone.tipo());
    }
}
