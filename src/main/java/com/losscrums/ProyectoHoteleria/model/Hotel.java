package com.losscrums.ProyectoHoteleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para cada hotel

    // Nombre del hotel
    private String name; 
     // Dirección del hotel
    private String address;
    // Número de estrellas del hotel
    private Long numStars; 
    // Nivel de confort del hotel
    private String comfort; 
}
