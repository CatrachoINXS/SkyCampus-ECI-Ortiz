package com.skycampus.service;

import com.skycampus.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardServiceTest {
    private DashboardService service;

    private static final Sede ECI = new Sede("ECI", 10);
    private static final Sede UNAL = new Sede("UNAL", 5);
    private static final Sede EAFIT = new Sede("EAFIT", 0);
    private static final Sede UNIANDES = new Sede("UNIANDES", 0);

    private static final Drone D1 = new Drone("D-01", "Modelo 1", 90, true, "Bloque A", TipoDrone.EXPRESS);
    private static final Drone D2 =new Drone("D-02", "Modelo 2", 91, true, "Bloque B", TipoDrone.EXPRESS);

    @BeforeEach
    void setUp() {
        service = new DashboardService();
    }

    static Stream<Arguments> proveerEscenarios() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        List.of(EAFIT)
                ),
                Arguments.of(
                        List.of(
                                new Mision("M1", D1, "Origen", "Destino", TipoCarga.SOBRE, EstadoMision.ENTREGADA, Prioridad.URGENTE, "", LocalTime.now(), 200, ECI)
                        ),
                        List.of(ECI)
                ),
                Arguments.of(
                        List.of(
                                new Mision("M1", D1, "Origen", "Destino", TipoCarga.SOBRE, EstadoMision.ENTREGADA, Prioridad.NORMAL, "", LocalTime.now(), 200, ECI),
                                new Mision("M2", D2, "Origen", "Destino", TipoCarga.CARPETA, EstadoMision.FALLIDA, Prioridad.URGENTE, "", LocalTime.now(), 300, ECI)
                        ),
                        List.of(ECI)
                ),
                Arguments.of(
                        List.of(
                                new Mision("M1", D1, "Origen", "Destino", TipoCarga.SOBRE, EstadoMision.ENTREGADA, Prioridad.URGENTE, "", LocalTime.now(), 200, ECI),
                                new Mision("M2", D2, "Origen", "Destino", TipoCarga.LIBRO, EstadoMision.FALLIDA, Prioridad.NORMAL, "", LocalTime.now(), 500, UNAL)
                        ),
                        List.of(ECI, UNAL, EAFIT, UNIANDES)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("proveerEscenarios")
    @DisplayName("Consulta 1: Validación de Tasa de Éxito por Sede")
    void probarTasaExitoPorSede(List<Mision> misiones, List<Sede> sedes) {
        Map<Sede, Double> resultado = service.calcularTasaExitoPorSede(misiones, sedes);

        assertNotNull(resultado);
        assertEquals(sedes.size(), resultado.size());
        sedes.forEach(sede -> assertTrue(resultado.get(sede) >= 0.0 && resultado.get(sede) <= 1.0));
    }
    @ParameterizedTest
    @MethodSource("proveerEscenarios")
    @DisplayName("Consulta 2: Validación de Tiempo Promedio de Entrega por Sede")
    void probarTiempoPromedioPorSede(List<Mision> misiones, List<Sede> sedes) {
        Map<Sede, Double> resultado = service.calcularTiempoPromedioPorSede(misiones, sedes);

        assertNotNull(resultado);
        assertEquals(sedes.size(), resultado.size());
        sedes.forEach(sede -> assertTrue(resultado.get(sede) >= 0.0));
    }

    @ParameterizedTest
    @MethodSource("proveerEscenarios")
    @DisplayName("Consulta 3: Validación de Porcentaje de Misiones Urgentes por Sede")
    void probarPorcentajeUrgentesPorSede(List<Mision> misiones, List<Sede> sedes) {
        Map<Sede, Double> resultado = service.calcularPorcentajeUrgentesPorSede(misiones, sedes);

        assertNotNull(resultado);
        assertEquals(sedes.size(), resultado.size());
        sedes.forEach(sede -> assertTrue(resultado.get(sede) >= 0.0 && resultado.get(sede) <= 1.0));
    }

    @ParameterizedTest
    @MethodSource("proveerEscenarios")
    @DisplayName("Consulta 4: Validación de Drone Más Utilizado por Sede")
    void probarDroneMasUtilizadoPorSede(List<Mision> misiones, List<Sede> sedes) {
        Map<Sede, Optional<Drone>> resultado = service.calcularDroneMasUtilizadoPorSede(misiones, sedes);

        assertNotNull(resultado);
        assertEquals(sedes.size(), resultado.size());
        sedes.forEach(sede -> assertNotNull(resultado.get(sede)));
    }
}
