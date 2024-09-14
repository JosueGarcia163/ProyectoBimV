package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;

public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByHotel (Hotel hotel);


}
