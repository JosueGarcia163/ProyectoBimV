package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.model.Reservation;

public interface  HotelRepository extends JpaRepository<Hotel, Long>{
    List<Hotel> findByReservation(Reservation reservation);
}