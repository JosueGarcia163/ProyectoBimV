package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Room;

public interface IRoomService {

    List<Room> listRoom();

    Room findRoom(Long id);

    Room saveRoom(Room habitacion);

    public void deleteRoom(Room habitacion);

    //List<Habitacion> findByRoomType(String roomType);

    //List<Habitacion> findAvailable(Boolean availability);

    //List<Habitacion> findByCapacity(String capacity);
}
