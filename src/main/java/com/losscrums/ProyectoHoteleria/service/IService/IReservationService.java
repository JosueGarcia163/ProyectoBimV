package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;
import java.util.Optional;

import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.User;

public interface IReservationService {
    
    // Method to find all reservations
    List<Reservation> findAll();

    // Method to find a reservation by its ID
    Optional<Reservation> findById(Long id);

    // Method to save a reservation
    Reservation save(Reservation reservation);

    // Method to delete a reservation by ID
    void deleteById(Long id);

    // Method to find reservations by user
    List<Reservation> findByUser(User user);
}
