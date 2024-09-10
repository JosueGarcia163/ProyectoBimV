package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Habitacion;

public interface IHabitacionService {

    List<Habitacion> listRoom();

    Habitacion findRoom(Long id);

    Habitacion saveRoom(Habitacion habitacion);

    public void deleteRoom(Long id);

    //List<Habitacion> findByRoomType(String roomType);

    //List<Habitacion> findAvailable(Boolean availability);

    //List<Habitacion> findByCapacity(String capacity);
}
