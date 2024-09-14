package com.losscrums.ProyectoHoteleria.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent; // Identificador único para cada evento

    // Tipo de evento
    @NotBlank
    private String eventType;

    // Nombre del evento
    @NotBlank
    private String name;

    // Costo del evento
    @NotNull
    private double cost;

    // Fecha de inicio del evento
    @NotNull
    @FutureOrPresent
    private Timestamp dateStart;

    // Fecha de finalización del evento
    @NotNull
    @FutureOrPresent
    private Timestamp dateFinish;

    // Relación ManyToOne con Hotel
    @ManyToOne
    private Hotel hotel;
}
