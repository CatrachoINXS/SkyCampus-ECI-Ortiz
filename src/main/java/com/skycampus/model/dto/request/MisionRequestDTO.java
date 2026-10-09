package com.skycampus.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class MisionRequestDTO {

    private String sedeOrigen;
    private String destino;
    private Integer pesoPaquete;
    private String prioridad;
    
}
