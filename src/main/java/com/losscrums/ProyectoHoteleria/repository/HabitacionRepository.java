package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
   
}
