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



@RestController // esta anotacion implementa @Controller @ResponseBody
@RequestMapping("/hoteleria/v1") //Esta es la ruta general del controlador
public class HabitacionController {

    @Autowired
    HabitacionService habitacionService;

    // Rutas especificas para cada fumcion del programa

    /*
     * Metodo de para listar las habitaciones
     * @return ResponseEntity
     */
    @GetMapping("/list/habitacion")
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        // Se inyecta la dependencia del servicio de habitaciones
        try{
            return ResponseEntity.ok().body(habitacionService.listRoom());
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

    @PostMapping("/post/habitacion")
    public ResponseEntity<?> addHabitacion(
        /* multipart-formadata
        La anotacion @Valid ejecuta las validaciones del bean de DTO
        RequestBody para el JSON 
        ModelAttribute para los archivos tambien */
        @Valid @ModelAttribute HabitacionDTO habitacion,
        //BindingResult hace la captura de los errores si en tal no pasa las validaciones
        BindingResult result
    ){
        Map<String, Object> res = new HashMap<>();
        //La verificacion si hay errores de validaciones del result
        if(result.hasErrors()){
            //Obtiene una lista de todos los errores de campo del BindingResult
            List<String> erros = result.getFieldErrors().stream().
            //Almacena todos los mensajes de error de la lista
            map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            res.put("message", "Error con las validaciones, por favor ingresa todos los campos");
            res.put("Errors", erros);
            //HTTP con el estado 400 (Bad Request)
            return ResponseEntity.badRequest().body(res);
        }
        try {
            //utilizamos los atributos del bean DTO de habitaciones
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
        // La inyeccion de la depencia del servicio de habitaciones
        try{
            Habitacion habitacion = habitacionService.findRoom(id);
            return ResponseEntity.ok().body(habitacion);
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

    // Creamos el metodo de editar habitacion
    @PutMapping("/put/habitacion/{id}")
    // Utilizamos la clase reponseEntity el cual espara un valor de repuesta
    public ResponseEntity<?> editRoom(
            //Esperamos que se nos otorgue la id con el que vamos a editar
            @PathVariable long id,
            /*Se ejecutan las validaciones que definimos en HabitacionDTO, con  la anotacion @ModelAttribute 
            indicamos que el parametro habitacion debe ser enlazado con los datos del formulario en la 
            solicitud del multipart */            
            @Valid @ModelAttribute HabitacionDTO habitacion,
            /* Es una interfaz que se usa para capturar y manejar los errores de validación que pueden ocurrir 
            durante el proceso de enlace de datos */
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
            // Busca la habitacion existente en la bd por su id
            Habitacion existingHabitacion = habitacionService.findRoom(id);
            if (existingHabitacion == null) {
                res.put("message", "No se pudo encontrar la habitacion con la identicacion proporcionada");
                return ResponseEntity.internalServerError().body(res);
            }
            // Actualiza los datos de la habitacion con los valores nuevos del bean DTO
            // de habitacion
            existingHabitacion.setRoomType(habitacion.getRoomType());
            existingHabitacion.setCapacity(habitacion.getCapacity());
            existingHabitacion.setAvailability(habitacion.getAvailability());
            existingHabitacion.setAvailabilityDate(habitacion.getAvailabilityDate());
            // Se guarda los cambios en el servicio den habitacion
            habitacionService.saveRoom(existingHabitacion);
            res.put("message", "Habitacion ha sido actualizada correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    //Se cera el metodo de eliminar habitacion
    @DeleteMapping("/delete/habitacion/{id}")
    // Se utiliza la clase responseEntity con la clase hashMap para devolver un String
    // y un Object
    public ResponseEntity<Map<String, Object>> deleteRoom(
        // se espera el id para que se pueda buscar la habitacion que se va a eliminar    
        @PathVariable long id){
        Map<String, Object> res = new HashMap<>();
        try{
            //Buscamos el id de la habitacion que se va eliminar
            Habitacion habitacion = habitacionService.findRoom(id);
            //Creamos un if para validar si se encuentra el id
            if(habitacion == null){
                //Modificamos el Map de res para que nos muestre este mensaje junto aun error del
                //protocolo HTTP
                res.put("message", "No se encontro la habitacion con la identificacion proporcionada");
                //El responseEntity retorna un HTTP no encontrado
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            //Eliminamos la habitacion que se solicito
            habitacionService.deleteRoom(habitacion);
            res.put("message", "Habitacion eliminada correctamente");
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            //Utilizamos la expecion para ubicar que error hubo a la hora de eliminar
            res.put("message", "Error al intentar eliminar la habitacion");
            res.put("error", e.getMessage());
            res.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
