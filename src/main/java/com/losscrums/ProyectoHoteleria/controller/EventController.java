package com.losscrums.ProyectoHoteleria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.losscrums.ProyectoHoteleria.DTO.EventResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.EventSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.service.EventService;
import com.losscrums.ProyectoHoteleria.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hoteleria/v1/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    HotelService hotelService;

    //Funcion para encontrar evento por Id hotel.
    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getEventforHotel(@PathVariable Long hotelId) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<EventResponseDTO> eventSaveDTOs = eventService.getEventforHotel(hotelId);
            //Validacion, si no encuentra nada por medio del Id.
            if (eventSaveDTOs == null || eventSaveDTOs.isEmpty()) {
                res.put("message", "Aún no tienes eventos creados");
                return ResponseEntity.status(404).body(res);
            } else {
                return ResponseEntity.ok(eventSaveDTOs);
            }
        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> saveEvent(
            @Valid @ModelAttribute EventSaveDTO eventDTO,
            BindingResult result
    ) {
        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Event event = eventService.saveEvent(eventDTO);
            res.put("message", "Reservación guardada exitosamente");
            res.put("reservation", event);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la reservacion, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listEvent() {
        Map<String, Object> res = new HashMap<>();
        // Se inyecta la dependencia del servicio de habitaciones
        try {
            return ResponseEntity.ok().body(eventService.listEvent());
            // Aqui se captura los posibles errores
        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al momento de conectarse a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        } catch (DataAccessException err) {
            res.put("Message", "Error al momento de consultar a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/list/{idEvent}")
    public ResponseEntity<?> findEventId(@PathVariable long idEvent) {
        Map<String, Object> res = new HashMap<>();
        // La inyeccion de la depencia del servicio de habitaciones
        try {
            Event event = eventService.findEvent(idEvent);
            return ResponseEntity.ok().body(event);
            // Aqui capturas posibles errores
        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al momento de conectarse a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        } catch (DataAccessException err) {
            res.put("Message", "Error al momento de consultar a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/put/{idEvent}")
    public ResponseEntity<?> updateEvent(
            @PathVariable Long idEvent, // Recibe el ID del evento en la URL
            @ModelAttribute EventSaveDTO eventDTO, // Recibe el objeto EventSaveDTO con los datos actualizados
            BindingResult result) {

        if (result.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("Errors", result.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Llamar al servicio para actualizar el evento
            Event updatedEvent = eventService.editEvent(idEvent, eventDTO);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el evento");
        }
    }

}
