package com.losscrums.ProyectoHoteleria.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.repository.ReservationRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IReservationService;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(ReservationSaveDTO reservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ReservationResponseDTO> findByUser(long userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}

