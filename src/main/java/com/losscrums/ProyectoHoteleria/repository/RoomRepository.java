package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {
   
}
