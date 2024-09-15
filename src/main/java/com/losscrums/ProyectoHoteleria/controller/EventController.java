package com.losscrums.ProyectoHoteleria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            List<EventSaveDTO> eventSaveDTOs = eventService.getEventforHotel(hotelId);
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

    



}
