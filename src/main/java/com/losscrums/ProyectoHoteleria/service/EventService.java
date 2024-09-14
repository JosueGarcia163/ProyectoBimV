package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.repository.EventRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IEventService;
;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> listarEventos() {
        return eventRepository.findAll();
    }

    @Override
    public Event buscarEvento(Long idEvent) {
        // Buscar evento por ID, si no se encuentra retorna null
        return eventRepository.findById(idEvent).orElse(null);
    }

    @Override
    public Event guardarEvento(Event event) {
        // Guardar o actualizar un evento
        return eventRepository.save(event);
    }

    @Override
    public void eliminarEvento(Event event) {
        // Eliminar un evento
        eventRepository.delete(event);
    }
    @Override
    public List<Event> listarEventosPorHotel(Hotel hotel) {
        // Encontrar todos los eventos asociados a un hotel
        return eventRepository.findByHotel(hotel);
    }
}