package com.skycampus.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class MisionResponseDTO {
    private String estado;
    private DroneResponseDTO droneAsignado;

    @Data 
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DroneResponseDTO {
        private String id;
    }
    
}
