package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Hotel;


public interface IHotelService {


    // Método para listar todos los hoteles
    public List<Hotel> listHotel();
    
    // Método para buscar un hotel por su ID
    public Hotel findHotel(Long id);
    
    // Método para guardar un hotel
    public Hotel saveHotel(Hotel hotel);
    
    // Método para eliminar un hotel
    public void deleteHotel(Hotel hotel);

    //Creamos la lista que utilizaremos en el servicio para poder buscar por nombre.
    public List<Hotel> getHotelsByName(String name);

    //Creamos la funcion para listar por medio del contador.
    public List<Hotel> listHotelsOrderedByNameCounter();

}




