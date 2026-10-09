package com.skycampus;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.skycampus.model.ServicioAerocivil;
import com.skycampus.model.ServicioClima;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest 
@AutoConfigureMockMvc
public class MisionIntegrationTest {

    @Autowired MockMvc mvc;
    @MockBean ServicioClima climaMock;
    @MockBean ServicioAerocivil aerocivilMock;

    @Test
    void crearMision_climaApto_retornaOkConDroneAsignado() throws Exception {
        Mockito.when(aerocivilMock.solicitarPermiso(Mockito.any())).thenReturn(true);
        Mockito.when(climaMock.condicionesAptas(Mockito.any(), Mockito.any())).thenReturn(true);

        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"ECI\",\"destino\":\"UNAL\",\"pesoPaquete\":300,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.droneAsignado.id").exists())
            .andExpect(jsonPath("$.estado").value("EN_VUELO"));
    }

    @Test
    void crearMision_climaAdverso_retornaConflict() throws Exception {
        Mockito.when(aerocivilMock.solicitarPermiso(Mockito.any())).thenReturn(true);
        Mockito.when(climaMock.condicionesAptas(Mockito.any(), Mockito.any())).thenReturn(false);

        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"ECI\",\"destino\":\"UNAL\",\"pesoPaquete\":300,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isConflict());
    }

    @Test
    void crearMision_sinDrones_retornaNotFound() throws Exception {
        Mockito.when(aerocivilMock.solicitarPermiso(Mockito.any())).thenReturn(true);
        Mockito.when(climaMock.condicionesAptas(Mockito.any(), Mockito.any())).thenReturn(true);

        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"ECI\",\"destino\":\"UNAL\",\"pesoPaquete\":300,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isNotFound());
    }

    @Test
    void crearMision_paquetePesado_retornaBadRequest() throws Exception {
        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"ECI\",\"destino\":\"UNAL\",\"pesoPaquete\":50000,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void crearMision_aerocivilRechaza_retornaForbidden() throws Exception {
        Mockito.when(aerocivilMock.solicitarPermiso(Mockito.any())).thenReturn(false);

        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"ECI\",\"destino\":\"UNAL\",\"pesoPaquete\":300,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isForbidden());
    }

    @Test
    void crearMision_sedeInactiva_retornaBadRequest() throws Exception {
        mvc.perform(post("/api/misiones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"sedeOrigen\":\"EAFIT\",\"destino\":\"UNAL\",\"pesoPaquete\":300,\"prioridad\":\"NORMAL\"}"))
            .andExpect(status().isBadRequest());
    } 
}
