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

import com.losscrums.ProyectoHoteleria.DTO.ServiceResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ServiceSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Services;
import com.losscrums.ProyectoHoteleria.service.ServicesService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/hoteleria/v1/service")
public class ServiceController {

    @Autowired
    ServicesService servicesService;
    

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getServiceforEvent(@PathVariable Long eventId){
        Map<String, Object> res = new HashMap<>();
        try {
            List<ServiceResponseDTO> serviceSaveDTO = servicesService.getServiceforEvent(eventId);
            //La validacion si algun caso no encuentra el id
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
            res.put("message", "Servicio guardado exitosamente");
            res.put("reservation", services);
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar el servicio, intente de nevo mas tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listServices() {
        Map<String, Object> res = new HashMap<>();
        // Se inyecta la depencia del servicio de servicio
        try{
            return ResponseEntity.ok(servicesService.listServices());
            // Aqui se captura los posibles errores que puedan ocurrir
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
        // Se inyecta la depencia del servicio de servicio
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
    
    @PutMapping("/put/{idService}")
    public ResponseEntity<?> updateService(
        //Recibe el ID del servicio en la URL
        @PathVariable Long idService,
        //Recibe el Objeto de ServiceSaveDTO con los datos actualizados
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
                // Llama al servicio para actualizar el servicio
                Services updatServices = servicesService.editServices(idService, serviceDTO);
                return ResponseEntity.ok(updatServices);
            } catch (Exception errException) {
                return ResponseEntity.internalServerError().body("Error al actualizar el evento");
            }
        }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteService(
        // Recibe el onjeto de Services con los datos del servicio a eliminar
        @PathVariable long id) {
            Map<String, Object> res = new HashMap<>();
            try{
                //Busca en servicio en la base de datos
                Services existingServices = servicesService.findService(id);

                // Verifica si el servicio de verdad existe
                if(existingServices == null){
                    res.put("message", "No se encontro el servicio co la identificacion proporcionada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
                }

                //Elimina el servicio
                servicesService.deleteService(existingServices);
                res.put("message", "Servicio eliminado correctamente");
                res.put("success", true);
                return ResponseEntity.ok(res);
            }catch (Exception err){
                // Aqui captura cualquier error que pueda ocurrir al eliminar el servicio
                res.put("message", "Error al intentar eliminar el servicio");
                res.put("error", err.getMessage());
                res.put("success", false);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }
}

