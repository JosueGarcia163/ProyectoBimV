package com.losscrums.ProyectoHoteleria.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventSaveDTO {


    //Parametros que van a ver el front 
    private String eventType;

    private String name;

    // Costo del evento
    
    private double cost;

    // Fecha de inicio del evento
    @NotNull(message = "La fecha de inicio no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime dateStart;

    // Fecha de finalización del evento
    @NotNull(message = "La fecha de finalizacion no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime dateFinish;
    @NotNull(message = "No hay un hotel para reservar")
    private Long hotelId;
}
