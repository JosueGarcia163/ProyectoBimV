package com.losscrums.ProyectoHoteleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// La entidad Servicio representa un servicio disponible en el sistema de hotelería.
// Incluye atributos como el tipo de servicio, capacidad, costo y descripción.
@Entity
@Data
public class Servicio {

    // El identificador único del servicio.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    // El tipo de servicio (por ejemplo, 'Spa', 'Gimnasio', etc.).
    // Este campo no puede estar en blanco.
    @NotBlank
    private String typeService;

    // La capacidad máxima del servicio (por ejemplo, número de personas permitidas).
    // Este campo no puede ser nulo.
    @NotNull
    private Long capacity;

    // El costo del servicio.
    // Este campo no puede ser nulo.
    @NotNull
    private Double cost;

    // La descripción del servicio, proporcionando más detalles.
    // Este campo no puede estar en blanco.
    @NotBlank
    private String description;

    @NotBlank
    @ManyToOne
    private Event event;

}
