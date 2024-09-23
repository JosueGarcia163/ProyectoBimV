package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.RoomResponseDTO;
import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.repository.RoomRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IRoomService;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ReservationService reservationService;

    //Metodo listar habitacion.
    @Override
    public List<RoomResponseDTO> listRoom() {
        //Guardamos todo en una lista
        List<Room> rooms = roomRepository.findAll();

        //mapeamos la lista para hacer que aparezcan todos los datos, exceptuando el de Reservation.
        return rooms.stream()
                .map(room -> new RoomResponseDTO(
                room.getIdRoom(),
                room.getRoomType(),
                room.getCapacity(),
                room.getAvailability(),
                room.getAvailabilityDate(),
                room.getHotel(),
                room.getEvent(),
                room.getReservation().getIdReservation() // Solo el número de reservación.
        ))
                .collect(Collectors.toList());
    }

    //Este buscar nos sirve para los metodos guardar, editar y eliminar.
    @Override
    public Room findRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    //Esta funcion sirve para listar habitacion por medio de Hotel.
    @Override
    public List<RoomResponseDTO> getRoomforHotel(Long hotelId) {
        Hotel hotel = hotelService.findHotel(hotelId);
        List<Room> rooms = roomRepository.findByHotel(hotel);
        return rooms.stream()
                .map(room -> new RoomResponseDTO(
                room.getIdRoom(),
                room.getRoomType(),
                room.getCapacity(),
                room.getAvailability(),
                room.getAvailabilityDate(),
                room.getHotel(),
                room.getEvent(),
                room.getReservation().getIdReservation()
        ))
                .collect(Collectors.toList());
    }

    //Esta funcion sirve para listar habitacion por medio de Evento.
    @Override
    public List<RoomResponseDTO> getRoomforEvent(Long eventId) {
        Event event = eventService.findEvent(eventId);
        List<Room> rooms = roomRepository.findByEvent(event);
        return rooms.stream()
                .map(room -> new RoomResponseDTO(
                room.getIdRoom(),
                room.getRoomType(),
                room.getCapacity(),
                room.getAvailability(),
                room.getAvailabilityDate(),
                room.getHotel(),
                room.getEvent(),
                room.getReservation().getIdReservation()
        ))
                .collect(Collectors.toList());
    }

    //Esta funcion sirve para listar habitacion por medio de reservacion.
    @Override
    public List<RoomResponseDTO> getRoomforReservation(long reservationId) {
        Reservation reservation = reservationService.find(reservationId);
        List<Room> rooms = roomRepository.findByReservation(reservation);
        return rooms.stream()
                .map(room -> new RoomResponseDTO(
                room.getIdRoom(),
                room.getRoomType(),
                room.getCapacity(),
                room.getAvailability(),
                room.getAvailabilityDate(),
                room.getHotel(),
                room.getEvent(),
                room.getReservation().getIdReservation()
        ))
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponseDTO findRoomById(long id) {
        // Busca la habitación por su ID
        Room room = roomRepository.findById(id).orElse(null);

        // Se usa el DTO para mostar los datos de room, hotel, evento y unicamente el Id de reservacion.
        return new RoomResponseDTO(
                room.getIdRoom(),
                room.getRoomType(),
                room.getCapacity(),
                room.getAvailability(),
                room.getAvailabilityDate(),
                room.getHotel(),
                room.getEvent(),
                room.getReservation().getIdReservation() // Solo el ID de la reservación.
        );
    }

}
