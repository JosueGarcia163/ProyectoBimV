package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.repository.HotelRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IHotelService;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> listarHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel buscarHotel(Long id) {
        // Buscar hotel por ID, si no se encuentra retorna null
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public Hotel guardarHotel(Hotel hotel) {
        // Guardar o actualizar un hotel
        return hotelRepository.save(hotel);
    }

    @Override
    public void eliminarHotel(Hotel hotel) {
        // Eliminar un hotel
        hotelRepository.delete(hotel);
    }
}
