package com.losscrums.ProyectoHoteleria.model;
import java.sql.Timestamp;

import com.losscrums.ProyectoHoteleria.utils.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Entity // Hibernate Entity
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @NotNull
    @FutureOrPresent
    private Timestamp start;

    @NotNull
    @FutureOrPresent
    private Timestamp end;

    @NotBlank
    private String cost;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne
    private User user;
}
