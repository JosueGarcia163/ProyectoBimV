package com.losscrums.ProyectoHoteleria.DTO;

import java.sql.Timestamp;

import com.losscrums.ProyectoHoteleria.model.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {

    private Long idEvent;
    private String eventType;
    private String name;
    private double cost;
    private Timestamp dateStart;
    private Timestamp dateFinish;
    private Hotel hotel;
}
