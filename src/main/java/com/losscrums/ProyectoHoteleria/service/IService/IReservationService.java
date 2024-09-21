package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;

public interface IReservationService {
    
    // Method to find all reservations
    List<ReservationResponseDTO> findAll();

    // Method to find a reservation by its ID
    public Reservation findById(Long id);   

    // Method to save a reservation
    Reservation save(ReservationSaveDTO reservation);

    // Method to delete a reservation by ID
    void delete(Reservation reservation);

    // Method to find reservations by user
    List<ReservationResponseDTO> findByUser(long userId);

    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO);
}

