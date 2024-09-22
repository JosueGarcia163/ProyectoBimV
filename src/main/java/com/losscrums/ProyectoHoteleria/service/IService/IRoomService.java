package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.RoomResponseDTO;
import com.losscrums.ProyectoHoteleria.model.Room;

public interface IRoomService {

    List<Room> listRoom();

    Room findRoom(Long id);

    Room saveRoom(Room room);

    public void deleteRoom(Room room);

    //Creamos el metodo en la interfaz que nos permite listar room por id de hotel.
    List<RoomResponseDTO> getRoomforHotel(Long hotelId);

    //Creamos el metodo en la interfaz que nos permite listar room por id de evento.
    List<RoomResponseDTO> getRoomforEvent(Long eventId);

    List<RoomResponseDTO> getRoomforReservation(long reservationId);
}
