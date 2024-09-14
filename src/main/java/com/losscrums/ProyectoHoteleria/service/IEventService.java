package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Event;

public interface IEventService {

    // Método para listar todos los eventos
    public List<Event> listarEventos();

    // Método para buscar un evento por su ID
    public Event buscarEvento(Long idEvent);

    // Método para guardar un evento
    public Event guardarEvento(Event event);

    // Método para eliminar un evento
    public void eliminarEvento(Event event);
}
