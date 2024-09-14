package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;


public interface IEventService {

    // Método para listar todos los eventos
    public List<Event> listEvent();

    // Método para buscar un evento por su ID
    public Event findEvent(Long idEvent);

    // Método para guardar un evento
    public Event saveEvent(Event event);

    // Método para eliminar un evento
    public void deleteEvent(Event event);

    // Método para listar eventos por hotel
    List<Event> getEventforHotel(Hotel hotel);
}
