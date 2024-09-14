package com.losscrums.ProyectoHoteleria.DTO;

import com.losscrums.ProyectoHoteleria.model.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO {
    private Long idService;
    private String typeService;
    private Long capacity;
    private Double cost;
    private String description;
    private Event event;
}
