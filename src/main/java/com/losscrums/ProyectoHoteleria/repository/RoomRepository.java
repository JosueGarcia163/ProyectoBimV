package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.model.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {
   List<Room> findByEvent (Event event);
   List<Room> findByHotel (Hotel hotel);
}
