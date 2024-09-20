package com.losscrums.ProyectoHoteleria.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelSaveDTO {

    @NotBlank(message = "El nombre no puede ir vacio")
    // Nombre del hotel
    private String name;
    @NotBlank(message = "La direccion no puede ir vacia")
    // Dirección del hotel
    private String address;
    // Número de estrellas del hotel
    private Long numStars;
    @NotBlank(message = "El confort no puede ir vacio")
    // Nivel de confort del hotel
    private String comfort;
}
