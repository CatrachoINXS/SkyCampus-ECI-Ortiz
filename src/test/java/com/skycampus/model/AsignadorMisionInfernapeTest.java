package com.skycampus.model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.skycampus.repository.RepositorioFlota;
import com.skycampus.service.observer.ObservadorDrone;
import com.skycampus.service.strategy.DroneSelectionStrategy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AsignadorMisionInfernapeTest {
    @Mock
    private RepositorioFlota repo;
    @Mock
    private ServicioClima clima;
    @Mock
    private DroneSelectionStrategy estrategia;
    @Mock
    private ObservadorDrone notificador;
    @InjectMocks
    private AsignadorMisionInfernape asignador;

    @Test
    @DisplayName("Verifica que el método prueba() invoque a todos sus colaboradores")
    void verificarInvocacionDeMetodos() {
    
        asignador.prueba();
        verify(repo, times(1)).findDisponibles(any());
        verify(clima, times(1)).condicionesAptas(any(), any());
        verify(estrategia, times(1)).selectDrone(any(), any());
        verify(notificador, times(1)).onEstadoCambiado(any(), any());
    }
}
