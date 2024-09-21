package com.losscrums.ProyectoHoteleria.DTO;

import java.time.LocalDateTime;

import com.losscrums.ProyectoHoteleria.utils.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationSaveDTO {

    @NotNull(message = "La fecha de inicio no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime start;
    @NotNull(message = "La fecha de finalizacion no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime end;
    private String cost;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull(message = "No hay un usuario para reservar")
    private long userId;
}
