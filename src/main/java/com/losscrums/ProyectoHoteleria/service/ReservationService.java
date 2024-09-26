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

    // Retorna una lista de todas las reservas.
    @Override
    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        // Transforma cada Reservation en un ReservationResponseDTO, omitiendo la contraseña del usuario.
        return reservations.stream().map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    // Busca una reserva por su ID.
    // @param id el identificador único de la reserva.
    // @return un objeto ReservationResponseDTO correspondiente al ID, o null si no se encuentra.
    @Override
    public ReservationResponseDTO findById(Long id) {
        // Almacena lo que encontró en el repositorio en base al id.
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        // Convierte la reserva en un DTO usando el método responseDTO.
        return responseDTO(reservation);
    }

    // Guarda una nueva reserva.
    // @param reservationDTO el objeto ReservationSaveDTO con los datos de la reserva.
    // @return el objeto Reservation guardado.
    @Override
    public Reservation save(ReservationSaveDTO reservationDTO) {
        try {
            // Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP.
            Timestamp startDate = Timestamp.valueOf(reservationDTO.getStart());
            Timestamp endDate = Timestamp.valueOf(reservationDTO.getEnd());

            // Buscamos una reserva en base al ID del usuario.
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

    // Elimina una reserva.
    // @param reservation el objeto Reservation que se desea eliminar.
    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    // Encuentra reservas por ID de usuario.
    // @param userId el identificador único del usuario.
    // @return una lista de objetos ReservationResponseDTO correspondientes al usuario.
    @Override
    public List<ReservationResponseDTO> findByUser(long userId) {
        User user = userService.findUserById(userId);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    // Encuentra reservas por ID de habitación.
    // @param roomId el identificador único de la habitación.
    // @return una lista de objetos ReservationResponseDTO correspondientes a la habitación.
    @Override
    public List<ReservationResponseDTO> findByRoom(long roomId) {
        Room room = roomService.findRoom(roomId);
        List<Reservation> reservations = reservationRepository.findByRoom(room);
        return reservations.stream()
                .map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    // Busca una reserva por ID.
    // @param id el identificador único de la reserva.
    // @return el objeto Reservation correspondiente al ID, o null si no se encuentra.
    @Override
    public Reservation find(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    // Edita una reserva existente.
    // @param idReservation el identificador único de la reserva a editar.
    // @param reservationDTO el objeto ReservationSaveDTO con los nuevos datos.
    // @return el objeto Reservation actualizado.
    @Override
    public Reservation editReservation(Long idReservation, ReservationSaveDTO reservationDTO) {
        try {
            // Buscar la reserva existente por su ID.
            Reservation existingReservation = reservationRepository.findById(idReservation)
                    .orElseThrow(() -> new IllegalArgumentException("Reservation no encontrada con el ID: " + idReservation));

            // Actualizar los campos de la reserva existente con los nuevos datos.
            existingReservation.setStart(Timestamp.valueOf(reservationDTO.getStart()));
            existingReservation.setEnd(Timestamp.valueOf(reservationDTO.getEnd()));
            existingReservation.setCost(reservationDTO.getCost());
            existingReservation.setStatus(reservationDTO.getStatus());

            // Buscar el usuario asociado y actualizar la reserva.
            User user = userService.findUserById(reservationDTO.getUserId());
            Room room = roomService.findRoom(reservationDTO.getRoomId());
            existingReservation.setUser(user);
            existingReservation.setRoom(room);

            // Guardar la reserva actualizada.
            return reservationRepository.save(existingReservation);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al editar la reservation", err);
        }
    }

    // Método privado para listar solo los datos de las reservas y los datos específicos del usuario
    // (sin mostrar credenciales importantes).
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
