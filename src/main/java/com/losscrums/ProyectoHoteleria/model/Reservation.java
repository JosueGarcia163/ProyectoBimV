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
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode
@Entity // Representa una entidad JPA
@AllArgsConstructor // Genera un constructor con todos los campos
@NoArgsConstructor // Genera un constructor sin argumentos
public class Reservation {

    @Id // Indica la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de ID
    private Long idReservation; // Identificador único de la reserva

    @NotNull // Asegura que el timestamp de inicio no sea nulo
    @FutureOrPresent // Asegura que el timestamp de inicio sea en el presente o futuro
    private Timestamp start; // Timestamp de inicio de la reserva

    @NotNull // Asegura que el timestamp de fin no sea nulo
    @FutureOrPresent // Asegura que el timestamp de fin sea en el presente o futuro
    private Timestamp end; // Timestamp de fin de la reserva

    @NotBlank // Asegura que el costo no esté vacío
    private String cost; // Costo de la reserva

    @Enumerated(EnumType.STRING) // Almacena el estado como una cadena
    private Status status; // Estado de la reserva (por ejemplo, confirmada, cancelada)

    @NotNull // Asegura que el usuario no sea nulo
    @ManyToOne // Muchas reservas pueden pertenecer a un usuario
    private User user; // Usuario que realizó la reserva

    @NotNull // Asegura que la habitación no sea nula
    @OneToOne // Cada reserva corresponde a una habitación
    private Room room; // Habitación asociada con la reserva
}
