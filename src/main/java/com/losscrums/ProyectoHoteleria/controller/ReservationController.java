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

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ReservationSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hoteleria/v1/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/list")
    public ResponseEntity<?> listReservation() {
        //Se crea el hash map para poder retornar mensajes.
        Map<String, Object> res = new HashMap<>();
        try {
            //Como estamos en el metodo listar listamos todo por medio del servicio de reservacion.
            return ResponseEntity.ok().body(reservationService.findAll());
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

    @GetMapping("/list/{idReservation}")
    public ResponseEntity<?> getReservationById(@PathVariable Long idReservation) {
        Map<String, Object> res = new HashMap<>();
        try {
            ReservationResponseDTO reservation = reservationService.findById(idReservation);

            // Validación, si no encuentra ninguna reserva con el ID proporcionado
            if (reservation == null) {
                res.put("message", "No se encontró una reservación con el ID proporcionado");
                return ResponseEntity.status(404).body(res);
            } else {
                return ResponseEntity.ok(reservation);
            }

        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> saveReservation(
            @Valid @ModelAttribute ReservationSaveDTO reservationDTO,
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
            reservationService.save(reservationDTO);
            res.put("message", "Reservación guardada exitosamente");

            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar la reservacion, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PutMapping("/put/{idReservation}")
    public ResponseEntity<?> updateReservation(
            @PathVariable Long idReservation, // Recibe el ID del reservation en la URL
            @ModelAttribute ReservationSaveDTO reservationDTO, // Recibe el objeto ReservationSaveDTO con los datos actualizados
            BindingResult result) {
        //Creamos hashmap para poder mostar respuestas
        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("Errors", result.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Llamar al servicio para actualizar el reservation
            reservationService.editReservation(idReservation, reservationDTO);
            res.put("message", "reservacion actualizada correctamente.");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar la reservation");
        }
    }

    //Creamos el metodo de listar por usuario.
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> ReservationsByUser(@PathVariable Long userId) {
        //Creamos el hashMap
        Map<String, Object> res = new HashMap<>();
        try {
            //buscamos por id de usuario las reservaciones y la almacenamos en una variable de la lista ReservationResponseDTO.
            List<ReservationResponseDTO> reservations = reservationService.findByUser(userId);
            //Verificamos si la lista de reservacion esta vacia.
            if (reservations == null || reservations.isEmpty()) {
                res.put("message", "Aún no tienes reservaciones creadas");
                return ResponseEntity.status(404).body(res);
            } else {
                return ResponseEntity.ok(reservations);
            }
        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteReservation(
            @PathVariable long id) { // Recibe el objeto Reservation con los datos de la reservation a eliminar
        Map<String, Object> res = new HashMap<>();
        try {
            // Buscar el reservation en la base de datos
            Reservation existingReservation = reservationService.find(id);

            // Verificar si el reservation existe
            if (existingReservation == null) {
                res.put("message", "No se encontró el reservation con la identificación proporcionada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            // Eliminar el reservation
            reservationService.delete(existingReservation);
            res.put("message", "reservation eliminada correctamente");
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            // Capturar cualquier error que ocurra al eliminar el reservation
            res.put("message", "Error al intentar eliminar la reservation");
            res.put("error", e.getMessage());
            res.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }


   @GetMapping("/room/{RoomId}")
    public ResponseEntity<?> getReservationforRoom(@PathVariable Long RoomId) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<ReservationResponseDTO> reservationSaveDTOs = reservationService.findByRoom(RoomId);
            //Validacion, si no encuentra nada por medio del Id.
            if (reservationSaveDTOs == null || reservationSaveDTOs.isEmpty()) {
                res.put("message", "Aún no tienes reservas creados");
                return ResponseEntity.status(404).body(res);
            } else {
                return ResponseEntity.ok(reservationSaveDTOs);
            }
        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }


}
