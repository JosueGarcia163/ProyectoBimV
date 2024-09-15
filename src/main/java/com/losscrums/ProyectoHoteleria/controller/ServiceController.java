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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.losscrums.ProyectoHoteleria.DTO.ServiceResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ServiceSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Services;
import com.losscrums.ProyectoHoteleria.service.EventService;
import com.losscrums.ProyectoHoteleria.service.ServicesService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/hoteleria/v1/service")
public class ServiceController {

    @Autowired
    ServicesService servicesService;
    
    @Autowired
    EventService eventService;

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getServiceforEvent(@PathVariable Long eventId){
        Map<String, Object> res = new HashMap<>();
        try {
            List<ServiceResponseDTO> serviceSaveDTO = servicesService.getServiceforEvent(eventId);
            if(serviceSaveDTO == null || serviceSaveDTO.isEmpty()){
                res.put("message", "Aun no tienes servicios creados");
                return ResponseEntity.status(400).body(res);
            }else {
                return ResponseEntity.ok(serviceSaveDTO);
            }
        } catch (Exception err) {
            res.put("message","Error general al obtener los datos");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> saveService(
        @Valid @ModelAttribute ServiceSaveDTO serviceDTO,
        BindingResult result
    ){
        Map<String, Object> res = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Services services = servicesService.saveServices(serviceDTO);
            res.put("message", "Reservacion guardada exitosamente");
            res.put("reservation", services);
            return ResponseEntity.badRequest().body(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar el servicio, intente de nevo mas tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listServices() {
        Map<String, Object> res = new HashMap<>();

        try{
            return ResponseEntity.ok(servicesService.listServices());
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

    @GetMapping("/list/{idService}")
    public ResponseEntity<?> findServiceId(@PathVariable long idService){
        Map<String, Object> res = new HashMap<>();
        try {
            Services services = servicesService.findService(idService);
            return ResponseEntity.ok().body(services);
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
    
    @GetMapping("/put/{idService}")
    public ResponseEntity<?> updateService(
        @PathVariable Long idService,
        @ModelAttribute ServiceSaveDTO serviceDTO,
        BindingResult result) {
            if(result.hasErrors()){
                Map<String, Object> errors = new HashMap<>();
                errors.put("Errors", result.getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList()));
                return ResponseEntity.badRequest().body(errors);
            }
            try {
                Services updatServices = servicesService.editServices(idService, serviceDTO);
                return ResponseEntity.ok(updatServices);
            } catch (Exception errException) {
                return ResponseEntity.internalServerError().body("Error al actualizar el evento");
            }
        }
    
    
}
