package com.losscrums.ProyectoHoteleria.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceSaveDTO {
    @NotBlank(message = "El tipo de servicio no puede ir vacio")
    private String typeService;
    @NotNull(message = "La capacidad no puede ir vacia")
    private Long capacity;
    @NotNull(message = "El costo no puede ir vacio")
    private Double cost;
    @NotBlank(message = "La descripcion no puede ir vacia")
    private String description;
    @NotNull(message = "No se selecciono un evento para el servicio")
    private Long eventId;

}
