package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Hotel;


public interface IHotelService {


    // Método para listar todos los hoteles
    public List<Hotel> listHoteles();
    
    // Método para buscar un hotel por su ID
    public Hotel findHotel(Long id);
    
    // Método para guardar un hotel
    public Hotel saveHotel(Hotel hotel);
    
    // Método para eliminar un hotel
    public void deleteHotel(Hotel hotel);

}




