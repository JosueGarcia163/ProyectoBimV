package com.losscrums.ProyectoHoteleria.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String room;
    @NotBlank
    private String capacidad;
    @NotBlank
    private Boolean disponibilidad;
    
    @NotBlank
    private Date fechaDisponibilidad;
}