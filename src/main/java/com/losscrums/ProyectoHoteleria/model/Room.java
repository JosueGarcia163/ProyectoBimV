package com.losscrums.ProyectoHoteleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String roomType;
    @NotBlank
    private String capacity;
    @NotBlank
    private String availability;
    @NotBlank
    private String availabilityDate;
    @NotNull
    @ManyToOne
    private Hotel hotel;
    @NotNull
    //Esto nos permite conectar una unica habitacion a un unico evento.
    @OneToOne
    private Event event;

}
