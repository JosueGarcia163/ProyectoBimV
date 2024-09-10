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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.losscrums.ProyectoHoteleria.DTO.HotelDTO;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.service.CloudinaryService;
import com.losscrums.ProyectoHoteleria.service.HotelService;

import jakarta.validation.Valid;

@RestController // Implementa @Controller @ResponseBody
@RequestMapping("/hoteleria/v1/auth") // Ruta general
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    CloudinaryService cloudinaryService;

    // Rutas especificas

    /*
     * Metodo para listar usuarios
     * @return ResponseEntity con las diferentes 
     */
    @GetMapping("/list/hotel")
    public ResponseEntity<?> listHotel() {
        Map<String, Object> res = new HashMap<>();

        // Inyeccion de dependencia del servicio
        try {

            return ResponseEntity.ok().body(hotelService.listHotel());

            // Capturar posibles errores.
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

    @GetMapping("/find/hotel/{id}")
    public ResponseEntity<?> getHotel(@PathVariable long id) {
        Map<String, Object> res = new HashMap<>();

        // Inyeccion de dependencia del servicio
        try {
            Hotel hotel = hotelService.findHotel(id);
            return ResponseEntity.ok().body(hotel);

            //Capturamos errores.
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

    @PostMapping("/post/hotel")
    public ResponseEntity<?> postHotel(@RequestPart("profilePicture") MultipartFile profilePicture,
            // Multipart-formadata
            //@Valid ejecuta todas las validaciones del modelo DTO
            //RequestBody para el JSON / ModelAttribute para archivos tambien.

            @Valid @ModelAttribute HotelDTO hotel,
            //BindingResult captura los errores si en tal caso no pasa las validaciones.
            BindingResult result
    ) {

        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().
                    stream().map(error -> error.getDefaultMessage()).
                    collect(Collectors.toList());
            res.put("message", "Error en las validaciones porfavor ingresa en todos los campos.");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }

        try {
            //Creamos una variable map donde vamos a subir la imagen con el nombre profilesHotel
            Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture,
                    "profilesHotel");

            String urlProfilePicture = uploadResult.get("url").toString();
            String img = uploadResult.get("url").toString();
            Long id = null;
            Hotel newHotel = new Hotel(id, hotel.getName(), hotel.getAddress(), hotel.getNumStars(), hotel.getComfort(), img);
            //Utiliza servicios de cloudinary para subir la imagen que manda el usuario.
            hotelService.saveHotel(newHotel);
            res.put("message", "usuario recibido correctamente.");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);

        }
    }

    @PutMapping("/put/hotel/{id}")
    public ResponseEntity<?> editHotel(
            @PathVariable long id,
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid @ModelAttribute HotelDTO hotel,
            BindingResult result
    ) {

        Map<String, Object> res = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            res.put("message", "Error en las validaciones. Por favor ingresa todos los campos.");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }

        try {
            // Busca el hotel existente en la base de datos por su ID
            Hotel existingHotel = hotelService.findHotel(id);
            if (existingHotel == null) {
                res.put("message", "No se encontró el hotel con el ID proporcionado.");
                return ResponseEntity.internalServerError().body(res);
            }

            // Sube una nueva imagen si se le agrega.
            String img;
            if (!profilePicture.isEmpty()) {
                Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture, "profilesHotel");
                img = uploadResult.get("url").toString();
            } else {
                // Mantiene la imagen existente si no se le sube otra.
                img = existingHotel.getProfilePicture();
            }

            // Actualiza los datos del hotel con los valores nuevos del DTO
            existingHotel.setName(hotel.getName());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setNumStars(hotel.getNumStars());
            existingHotel.setComfort(hotel.getComfort());
            existingHotel.setProfilePicture(img);

            // Se guardan los cambios en el hotelService
            hotelService.saveHotel(existingHotel);

            res.put("message", "Hotel actualizado correctamente.");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @DeleteMapping("/delete/hotel/{id}")
    public ResponseEntity<Map<String, Object>> deleteHotel(@PathVariable long id) {

        Map<String, Object> res = new HashMap<>();
        try {
            Hotel hotel = hotelService.findHotel(id);
            if (hotel == null) {
                res.put("message", "No se encontró el hotel con el ID especificado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            hotelService.deleteHotel(hotel);
            res.put("message", "Hotel eliminado correctamente.");
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("message", "Error al intentar eliminar el hotel.");
            res.put("error", e.getMessage());
            res.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

}
