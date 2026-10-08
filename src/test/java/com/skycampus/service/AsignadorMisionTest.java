package com.skycampus.service;

import com.skycampus.model.*;
import com.skycampus.service.observer.ObservadorDrone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AsignadorMisionTest {
    @Mock
    private ApiMeteorologica clima;
    @Spy
    private GestorFlota gestorFlota = new GestorFlota();
    @Mock
    private ObservadorDrone notificador;
    @Spy
    private GestorMisiones gestorMisiones = new GestorMisiones();

    @InjectMocks
    private AsignadorMision asignador;

    private List<Drone> flota;

    @BeforeEach
    void setUp() {
        gestorFlota.suscribir(notificador);
        flota = List.of(
                new Drone("D-01", "Modelo 1", 85, true,  "Bloque A", TipoDrone.CARGO),
                new Drone("D-02", "Modelo 2", 42, false, "Biblioteca", TipoDrone.MINI),
                new Drone("D-03", "Modelo 3", 91, true,  "Bloque C", TipoDrone.EXPRESS),
                new Drone("D-04", "Modelo 4", 18, true,  "Bloque B", TipoDrone.EXPRESS),
                new Drone("D-05", "Modelo 5", 67, true,  "Bloque D", TipoDrone.CARGO)
        );
    }

    @Test
    @DisplayName("Drone de mayor batería se asigna a misión NORMAL")
    void misionNormal_asignaDroneMayorBateria() {
        Mockito.when(clima.esApto()).thenReturn(true);
        Mision mision = new MisionBuilder()
                .id("M-01")
                .origen("Bloque A")
                .destino("Bloque B")
                .tipoCarga(TipoCarga.SOBRE)
                .prioridad(Prioridad.NORMAL)
                .peso(200)
                .drone(flota.get(0))
                .build();
        Optional<Drone> asignado = asignador.asignarDrone(flota, mision);
        assertTrue(asignado.isPresent());
        assertEquals(91, asignado.get().bateria());
        Mockito.verify(notificador).onEstadoCambiado(Mockito.any(), Mockito.any());
    }

    @Test
    void climaAdverso_retornaVacio() {
        Mockito.when(clima.esApto()).thenReturn(false);
        Mision mision = new MisionBuilder()
                .id("M-01")
                .origen("Bloque A")
                .destino("Bloque B")
                .peso(300)
                .drone(flota.get(0))
                .build();
        Optional<Drone> resultado = asignador.asignarDrone(flota, mision);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void sinDronesAptos_retornaVacio() {
        Mockito.when(clima.esApto()).thenReturn(true);
        List<Drone> flotaIncapaz = List.of(
                new Drone("D-01", "Modelo 1", 10, true,  "Bloque A", TipoDrone.CARGO)
        );
        Mision mision = new MisionBuilder()
                .id("M-01")
                .origen("Bloque A")
                .destino("Bloque C")
                .peso(300)
                .drone(flota.get(0))
                .build();
        Optional<Drone> resultado = asignador.asignarDrone(flotaIncapaz, mision);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void paqueteMuyPesado_retornaVacio() {
        Mockito.when(clima.esApto()).thenReturn(true);
        Mision misionExcedida = new MisionBuilder()
                .id("M-04")
                .origen("Bloque A")
                .destino("Bloque C")
                .peso(3000)
                .drone(flota.get(0))
                .build();
        Optional<Drone> resultado = asignador.asignarDrone(flota, misionExcedida);
        assertTrue(resultado.isEmpty());
        Mockito.verifyNoInteractions(notificador);
    }

    @Test
    void misionUrgente_asignaDroneExpressMayorBateria() {
        Mockito.when(clima.esApto()).thenReturn(true);
        Mision misionUrgente = new MisionBuilder()
                .id("M-05")
                .origen("Bloque A")
                .destino("Bloque B")
                .prioridad(Prioridad.URGENTE)
                .peso(300)
                .drone(flota.get(0))
                .build();
        Optional<Drone> resultado = asignador.asignarDrone(flota, misionUrgente);
        assertTrue(resultado.isPresent());
        assertEquals("D-03", resultado.get().id());
        assertEquals(TipoDrone.EXPRESS, resultado.get().tipo());
        Mockito.verify(notificador).onEstadoCambiado(resultado.get(), EstadoDrone.EN_VUELO);
    }


}
