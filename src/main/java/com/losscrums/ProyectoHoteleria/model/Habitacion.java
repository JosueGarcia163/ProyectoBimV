package com.losscrums.ProyectoHoteleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

 // La entidad Habitacion representa una habitación en el sistema de hotelería.
 // Incluye atributos como tipo de habitación, capacidad, disponibilidad y
 // la fecha de disponibilidad.
@Entity
@Data
public class Habitacion {

     // El identificador único de la habitación.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     // El tipo de habitación (por ejemplo, 'Sencilla', 'Doble', etc.).
    private String roomType;

     // La capacidad de la habitación (por ejemplo, '2 personas', '4 personas').
     // Este campo no puede estar en blanco.
    @NotBlank
    private String capacity;

     // Indica si la habitación está disponible o no.
     // Se representa como una cadena y no puede estar en blanco.
    @NotBlank
    private String availability;

     // La fecha a partir de la cual la habitación estará disponible.
     // Se representa como una cadena de texto (por ejemplo, '2023-09-10') y no puede estar en blanco.
    @NotBlank
    private String availabilityDate;
}
