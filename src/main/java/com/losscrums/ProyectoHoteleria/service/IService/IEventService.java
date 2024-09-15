package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.EventResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.EventSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Event;


public interface IEventService {

    // Método para listar todos los eventos
    public List<Event> listEvent();

    // Método para buscar un evento por su ID
    public Event findEvent(Long idEvent);

    // Método para guardar un evento
    public Event saveEvent(EventSaveDTO eventDTO);

    // Método para eliminar un evento
    public void deleteEvent(Event event);

    // Método para listar eventos por hotel
    List<EventResponseDTO> getEventforHotel(Long hotelId);
}
