package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByRoomType(String roomType);

    List<Habitacion> findByAvailability(Boolean availability);

    List<Habitacion> findByCapacity(String capacity);
}
