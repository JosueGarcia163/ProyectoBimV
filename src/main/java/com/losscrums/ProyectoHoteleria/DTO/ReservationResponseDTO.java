package com.losscrums.ProyectoHoteleria.DTO;

import java.sql.Timestamp;

import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {

    private Long idReservation;
    private Timestamp start;
    private Timestamp end;
    private String cost;
    private Status status;
    private UserClearDTO user;
    private Room room;


}
