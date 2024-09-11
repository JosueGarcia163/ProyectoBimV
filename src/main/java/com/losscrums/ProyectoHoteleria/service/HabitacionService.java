package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

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
    public List<Habitacion> listRoom() {
        return habitacionRepository.findAll();
    }

    @Override
    public Habitacion findRoom(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    @Override
    public Habitacion saveRoom(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void deleteRoom(Habitacion habitacion) {
        habitacionRepository.delete(habitacion);;
    }

  
}
