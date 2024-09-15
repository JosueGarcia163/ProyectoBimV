package com.losscrums.ProyectoHoteleria.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.EventSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.repository.EventRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IEventService;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HotelService hotelService;

    //Funcion para listar todos los Eventos
    @Override
    public List<Event> listEvent() {
        return eventRepository.findAll();
    }

    //Funcion de tipo Evento que busca un evento en especial por su Id.
    @Override
    public Event findEvent(Long idEvent) {
        // Buscar evento por ID, si no se encuentra retorna null
        return eventRepository.findById(idEvent).orElse(null);
    }

    //Funcion que guarda un Evento.
    @Override
    public Event saveEvent(EventSaveDTO eventDTO) {
        try {
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp startDate = Timestamp.valueOf(eventDTO.getDateStart());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp endDate = Timestamp.valueOf(eventDTO.getDateFinish());

            Hotel hotel = hotelService.findHotel(eventDTO.getHotelId());

            //Despues de convertirlo lo pasa a Evento.
            Event event = new Event(
                    null,
                    eventDTO.getEventType(),
                    eventDTO.getName(),
                    eventDTO.getCost(), // Aquí se espera un double, no un String
                    startDate,
                    endDate,
                    hotel
            );
            return eventRepository.save(event);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al parsear las fechas", err);
        }// Guardar o actualizar un evento

    }

    //Metodo que declaramos en el IService que nos sirve para eliminar.
    @Override
    public void deleteEvent(Event event) {
        // Eliminar un evento
        eventRepository.delete(event);
    }

    //Funcion que nos sirve para poder buscar un evento por numero de Id de un hotel.
    @Override
    public List<EventSaveDTO> getEventforHotel(Long hotelId) {
        Hotel hotel = hotelService.findHotel(hotelId);
        List<Event> events = eventRepository.findByHotel(hotel);
        return events.stream()
                .map(event -> new EventSaveDTO(
                event.getEventType(),
                event.getName(),
                event.getCost(),
                event.getDateStart().toLocalDateTime(), // Convierte Timestamp a LocalDateTime
                event.getDateFinish().toLocalDateTime(), // Convierte Timestamp a LocalDateTime

                hotel.getId() // Asumiendo que el DTO también necesita el ID del hotel
        ))
                .collect(Collectors.toList());

        // Encontrar todos los eventos asociados a un hotel
        //return eventRepository.findByHotel(hotelId);
    }
}
