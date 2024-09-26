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
    public List<Hotel> listHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findHotel(Long id) {
        // Buscar hotel por ID, si no se encuentra retorna null
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        // Guardar o actualizar un hotel
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        // Eliminar un hotel
        hotelRepository.delete(hotel);
    }

    //Definimos la funcion de obtener hotel por medio de nombre.
    public List<Hotel> getHotelsByName(String name) {
        //Utilizamos la funcion que generamos en el repositorio para retornar un nombre de tipo String.
        return hotelRepository.findByName(name);
    }

    // Método para listar hoteles ordenados por nameCounter
    public List<Hotel> listHotelsOrderedByNameCounter() {
        //Utilizamos la funcion que generamos en el repositorio.
        return hotelRepository.findAllByOrderByNumberRentDesc();
    }
}
