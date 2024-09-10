package com.losscrums.ProyectoHoteleria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.losscrums.ProyectoHoteleria.DTO.HabitacionDTO;
import com.losscrums.ProyectoHoteleria.model.Habitacion;
import com.losscrums.ProyectoHoteleria.service.HabitacionService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/hoteleria/v1")    
public class HabitacionController {

    @Autowired
    HabitacionService habitacionService;

    @GetMapping("/list/habitacion")
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        try{
            return ResponseEntity.ok().body(habitacionService.listRoom());
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

    @PostMapping("/post/habitacion")
    public ResponseEntity<?> addHabitacion(
        @Valid @ModelAttribute HabitacionDTO habitacion,
        BindingResult result
    ){
        Map<String, Object> res = new HashMap<>();
        if(result.hasErrors()){
            List<String> erros = result.getFieldErrors().stream().
            map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            res.put("message", "Error con las validaciones, por favor ingresa todos los campos");
            res.put("Errors", erros);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Long id = null;
            Habitacion newHabitacion = new Habitacion(
                id,
                habitacion.getRoomType(),
                habitacion.getCapacity(),
                habitacion.getAvailability(),
                habitacion.getAvailabilityDate()
            );
            habitacionService.saveRoom(newHabitacion);
            res.put("message", "Habitacion recibida correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Eror al guardar la habitacion, intente mas tarde");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/list/habitacion/{id}")
    public ResponseEntity<?> getMethodRoomId(@PathVariable long id) {
        Map<String, Object> res = new HashMap<>();
        try{
            Habitacion habitacion = habitacionService.findRoom(id);
            return ResponseEntity.ok().body(habitacion);
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

    @PutMapping("/put/habitacion/{id}")
    public ResponseEntity<?> editRoom(
            @PathVariable long id,
            @Valid @ModelAttribute HabitacionDTO habitacion,
            BindingResult result
    ) {
        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().
            map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            res.put("message", "Error en las validaciones, por favor ingresa todos los campos.");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Habitacion existingHabitacion = habitacionService.findRoom(id);
            if (existingHabitacion == null) {
                res.put("message", "No se pudo encontrar la habitacion con la identicacion proporcionada");
                return ResponseEntity.internalServerError().body(res);
            }
            existingHabitacion.setRoomType(habitacion.getRoomType());
            existingHabitacion.setCapacity(habitacion.getCapacity());
            existingHabitacion.setAvailability(habitacion.getAvailability());
            existingHabitacion.setAvailabilityDate(habitacion.getAvailabilityDate());
            habitacionService.saveRoom(existingHabitacion);
            res.put("message", "Habitacion ha sido actualizada correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/habitacion/{id}")
    public ResponseEntity<Map<String, Object>> deleteRoom(
            @PathVariable long id){
        Map<String, Object> res = new HashMap<>();
        try{
            Habitacion habitacion = habitacionService.findRoom(id);
            if(habitacion == null){
                res.put("message", "No se encontro la habitacion con la identificacion proporcionada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            habitacionService.deleteRoom(habitacion);
            res.put("message", "Habitacion eliminada correctamente");
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("message", "Error al intentar eliminar la habitacion");
            res.put("error", e.getMessage());
            res.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
