package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    // Custom query method to find reservations by user
    List<Reservation> findByUser(User user);
}

