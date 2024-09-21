package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.DTO.UserClearDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.User;
import com.losscrums.ProyectoHoteleria.repository.ReservationRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IReservationService;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        // Transforma cada Reservation en un ReservationDTO, omitiendo la contraseña del usuario
        return reservations.stream().map(reservation -> responseDTO(
                reservation
        )).collect(Collectors.toList());

    }

    @Override
    public Reservation findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reservation save(ReservationSaveDTO reservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ReservationResponseDTO> findByUser(long userId) {
        User user = userService.findUserById(userId);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*Metodo que sirve para listar solamente los datos de las reservas y los datos especificos del usuario(sin mostar credenciales 
    importantes).
    para que se mande a llamar en la funcion de listar. */
    private ReservationResponseDTO responseDTO(Reservation reservation) {
        User user = reservation.getUser();

        UserClearDTO userDTO = new UserClearDTO(
                user.getName(),
                user.getSurname(),
                user.getUsername()
        );

        ReservationResponseDTO dto = new ReservationResponseDTO(
                reservation.getIdReservation(),
                reservation.getStart(),
                reservation.getEnd(),
                reservation.getCost(),
                reservation.getStatus(),
                userDTO
        );

        return dto;
    }

}
