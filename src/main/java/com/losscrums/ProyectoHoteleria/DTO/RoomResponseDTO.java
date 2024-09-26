package com.losscrums.ProyectoHoteleria.DTO;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private Long id;
    private String roomType;
    private String capacity;    
    private String availability;
    private String availabilityDate;
    private Hotel hotelId;
    private Event eventId;
}
