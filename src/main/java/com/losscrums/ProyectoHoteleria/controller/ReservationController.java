package com.losscrums.ProyectoHoteleria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.service.ReservationService;

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

}
