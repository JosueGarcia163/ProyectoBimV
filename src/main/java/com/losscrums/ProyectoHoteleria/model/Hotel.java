package com.losscrums.ProyectoHoteleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
    private Long idHotel; // Identificador único para cada hotel

    @NotBlank
    // Nombre del hotel
    private String name; 
    @NotBlank
     // Dirección del hotel
    private String address;
    // Número de estrellas del hotel
    private Long numStars; 
    @NotBlank
    // Nivel de confort del hotel
    private String comfort; 

    //No es obligatoria
    private String profilePicture;

    @ManyToOne
    private Reservation reservation;
    
}

