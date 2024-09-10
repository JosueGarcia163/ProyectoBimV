package com.losscrums.ProyectoHoteleria.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabitacionDTO {
    @NotBlank(message = "El tipo de habitacion no puede ir vacio")
    private String roomType;
    @NotBlank(message = "La capacidad no puede ir vacia")
    private String capacity;
    @NotBlank(message = "La disponibilidad no puede ir vacia")    
    private String availability;
    @NotBlank(message = "La fecha disponible no puede ir vacia")
    private String availabilityDate;
}
