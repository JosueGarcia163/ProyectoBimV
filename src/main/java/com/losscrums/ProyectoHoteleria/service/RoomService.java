package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.RoomResponseDTO;
import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;
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

    @Override
    public List<Room> listRoom() {
        return roomRepository.findAll();
    }

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
                room.getEvent()
        ))
                .collect(Collectors.toList());
    }

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
                room.getEvent()
        ))
                .collect(Collectors.toList());
    }

    

  
}
