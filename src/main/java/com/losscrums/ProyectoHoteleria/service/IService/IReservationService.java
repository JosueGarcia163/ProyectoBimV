package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;

public interface IReservationService {
    
    // Método para encontrar todas las reservas
    List<ReservationResponseDTO> findAll();

    // Método para encontrar una reserva por su ID
    ReservationResponseDTO findById(Long id);

    // Método para encontrar una reserva y permitir su eliminación
    Reservation find(Long id);

    // Método para guardar una nueva reserva
    Reservation save(ReservationSaveDTO reservation);

    // Método para eliminar una reserva por su ID
    public void delete(Reservation reservation);

    // Método para encontrar reservas por usuario
    List<ReservationResponseDTO> findByUser(long userId);

    // Método para editar una reserva existente
    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO);

    // Método para encontrar reservas por habitación
    List<ReservationResponseDTO> findByRoom(long roomId);
}
