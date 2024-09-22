package com.losscrums.ProyectoHoteleria.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Se utiliza la anotacion @Data para el creamiento de getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomSaveDTO {
    //Agregamos los atributos que despues vamos a utilizar para los
    //metodos en el controller
    @NotBlank(message = "El tipo de habitacion no puede ir vacio")
    private String roomType;
    @NotBlank(message = "La capacidad no puede ir vacia")
    private String capacity;
    @NotBlank(message = "La disponibilidad no puede ir vacia")    
    private String availability;
    @NotBlank(message = "La fecha disponible no puede ir vacia")
    private String availabilityDate;
    @NotNull(message = "No se seleccionó un hotel a reservar")
    private Long hotelId;
    @NotNull(message = "No se seleccionó un Evento a reservar")
    private Long eventId;
    @NotNull(message = "No se seleccionó una Reservacion a reservar")
    private Long reservationId;
}
