package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Hotel;


public interface IHotelService {


    // Método para listar todos los hoteles
    public List<Hotel> listarHoteles();
    
    // Método para buscar un hotel por su ID
    public Hotel buscarHotel(Long id);
    
    // Método para guardar un hotel
    public Hotel guardarHotel(Hotel hotel);
    
    // Método para eliminar un hotel
    public void eliminarHotel(Hotel hotel);

}



