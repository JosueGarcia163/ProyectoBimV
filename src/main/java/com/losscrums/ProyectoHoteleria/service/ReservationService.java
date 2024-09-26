package com.losscrums.ProyectoHoteleria.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.DTO.UserClearDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.model.User;
import com.losscrums.ProyectoHoteleria.repository.ReservationRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IReservationService;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Override
    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        // Transforma cada Reservation en un ReservationDTO, omitiendo la contraseña del usuario
        return reservations.stream().map(reservation -> responseDTO(
                reservation
        )).collect(Collectors.toList());

    }

    @Override
    public ReservationResponseDTO findById(Long id) {
        //Almacena lo que encontro en el repositorio en base al id.
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        // Convierte la reserva en un DTO usando el método responseDTO
        return responseDTO(reservation);
    }

    @Override
    public Reservation save(ReservationSaveDTO reservationDTO) {
        try {
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp startDate = Timestamp.valueOf(reservationDTO.getStart());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp endDate = Timestamp.valueOf(reservationDTO.getEnd());

            //Buscamos una reservacion en base al id de user.
            User user = userService.findUserById(reservationDTO.getUserId());

            Room room = roomService.findRoom(reservationDTO.getRoomId());

            Reservation reservation = new Reservation(
                    null,
                    startDate,
                    endDate,
                    reservationDTO.getCost(),
                    reservationDTO.getStatus(),
                    user,
                    room
            );
            return reservationRepository.save(reservation);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al parsear las fechas", err);
        }
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
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
    public List<ReservationResponseDTO> findByRoom(long roomId) {
        Room room = roomService.findRoom(roomId);
        List<Reservation> reservations = reservationRepository.findByRoom(room);
        return reservations.stream()
                .map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation find(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO) {
        try {
            // Buscar el reservation existente por su ID.
            Reservation existingReservation = reservationRepository.findById(idReservation)
                    .orElseThrow(() -> new IllegalArgumentException("Reservation no encontrada con el ID: " + idReservation));

            // Actualizar los campos del reservation existente con los nuevos datos
            existingReservation.setStart(Timestamp.valueOf(reservationDTO.getStart()));
            existingReservation.setEnd(Timestamp.valueOf(reservationDTO.getEnd()));
            existingReservation.setCost(reservationDTO.getCost());
            existingReservation.setStatus(reservationDTO.getStatus());

            // Buscar la user asociado y actualizar la reservation.
            User user = userService.findUserById(reservationDTO.getUserId());
            //Buscar room asociada al numero de reservacion para editar.
            Room room = roomService.findRoom(reservationDTO.getRoomId());
            existingReservation.setUser(user);
            existingReservation.setRoom(room);

            // Guardar el reservation actualizado
            return reservationRepository.save(existingReservation);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al editar el reservation", err);
        }
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
                userDTO,
                reservation.getRoom()
        );

        return dto;
    }

}
