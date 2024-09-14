package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.repository.RoomRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IRoomService;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository habitacionRepository;

    @Override
    public List<Room> listRoom() {
        return habitacionRepository.findAll();
    }

    @Override
    public Room findRoom(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    @Override
    public Room saveRoom(Room habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void deleteRoom(Room habitacion) {
        habitacionRepository.delete(habitacion);
    }

  
}
