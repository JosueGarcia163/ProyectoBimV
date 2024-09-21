package com.losscrums.ProyectoHoteleria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.losscrums.ProyectoHoteleria.DTO.ReservationResponseDTO;
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

    //Creamos el metodo de listar por usuario.
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> ReservationsByUser(@PathVariable Long userId) {
        Map<String, Object> res = new HashMap<>();
        try{
            List<ReservationResponseDTO> reservations = reservationService.findByUser(userId);
            if(reservations == null || reservations.isEmpty()){
                res.put("message", "Aún no tienes reservaciones creadas");
                return ResponseEntity.status(404).body(res);
            }else{
                return ResponseEntity.ok(reservations);
            }
        }catch(Exception err){
            res.put("message", "Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }
}
