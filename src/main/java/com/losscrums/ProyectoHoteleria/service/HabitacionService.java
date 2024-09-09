package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Habitacion;
import com.losscrums.ProyectoHoteleria.repository.HabitacionRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IHabitacionService;

@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> getAllRooms() {
        return habitacionRepository.findAll();
    }

    @Override
    public Optional<Habitacion> getRoomById(Long id) {
        return habitacionRepository.findById(id);
    }

    @Override
    public Habitacion saveRoom(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void deleteRoom(Long id) {
        habitacionRepository.deleteById(id);
    }

    @Override
    public List<Habitacion> findByRoomType(String roomType) {
        return habitacionRepository.findByRoomType(roomType);
    }

    @Override
    public List<Habitacion> findAvailable(Boolean availability) {
        return habitacionRepository.findByAvailability(availability);
    }

    @Override
    public List<Habitacion> findByCapacity(String capacity) {
        return habitacionRepository.findByCapacity(capacity);
    }
}
