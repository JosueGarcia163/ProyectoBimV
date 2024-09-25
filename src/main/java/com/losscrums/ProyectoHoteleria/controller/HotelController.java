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

import com.losscrums.ProyectoHoteleria.DTO.HotelSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.service.CloudinaryService;
import com.losscrums.ProyectoHoteleria.service.HotelService;
import com.losscrums.ProyectoHoteleria.service.ReservationService;

import jakarta.validation.Valid;

@RestController // Implementa @Controller @ResponseBody
@RequestMapping("/hoteleria/v1/hotel") // Ruta general
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ReservationService reservationService;

    // Rutas especificas

    /*
     * Metodo para listar usuarios
     * @return ResponseEntity con las diferentes 
     */
    @GetMapping("/list")
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

    @GetMapping("/list/requested")
    public ResponseEntity<?> listHotelsByNameCounter() {
        Map<String, Object> res = new HashMap<>();

        try {
            // Utilizamos el servicio para usar la funcion de listar por contador y guardarlo en una lista de hotel.
            List<Hotel> hotels = hotelService.listHotelsOrderedByNameCounter();

            // Devolvemos esa lista.
            return ResponseEntity.ok().body(hotels);

            //Captura de errores.
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

    @GetMapping("/list/{id}")
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

    //Creamos metodo de agregar.
    @PostMapping("/post")
    public ResponseEntity<?> postHotel(
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid @ModelAttribute HotelSaveDTO hotel,
            BindingResult result) {

        Map<String, Object> res = new HashMap<>();

        // Validaciones de DTO
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            res.put("message", "Error en las validaciones, por favor completa todos los campos.");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }

        try {
            // Subir imagen a Cloudinary y obtener la URL
            Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture, "profilesHotel");
            String img = uploadResult.get("url").toString();

            // Verificar si ya existen hoteles con el mismo nombre
            List<Hotel> existingHotels = hotelService.getHotelsByName(hotel.getName());
            int nameCounter = 1;

            //Verifica que la lista no este vacia, si no hay hoteles con el mismo nombre el codigo no se ejecuta.
            if (!existingHotels.isEmpty()) {
                // Si ya existen hoteles con el mismo nombre, incrementar el contador
                nameCounter = existingHotels.stream()
                        .mapToInt(Hotel::getNumberRent)//transforma existingHotels a enteros de la clase Hotel.
                        .max()//Busca el valor máximo de todos los números obtenidos a partir del método getNumberRent()
                        .orElse(0) + 1; // si la lista esta vacia devuelve 0, y se le suma 1 al valor maximo encontrado.
            }
            /*Creamos nuevo hotel
             * Lo creamos de esta forma ya que si lo creamos de la forma tradicional
             * el usuario estaria obligado a dar un valor al contador y nosotros
             * no queremos eso, para ello utilizamos los setters and getters 
             * para poder guardar los datos en hotel y asi poder darle el valor a 
             * contador sin que el usuario tenga que tocarlo.
             */

            Hotel newHotel = new Hotel();
            // Creamos hotelSaveDTO para poder guardarlo en newHotel.
            newHotel.setIdHotel(null);//id no lo tocamos por que se genera automaticamente
            newHotel.setName(hotel.getName());
            newHotel.setAddress(hotel.getAddress());
            newHotel.setNumStars(hotel.getNumStars());
            newHotel.setComfort(hotel.getComfort());
            newHotel.setProfilePicture(img);
            newHotel.setNumberRent(nameCounter); // se le asigna el contador.

            

            // Guardamos el nuevo hotel
            hotelService.saveHotel(newHotel);
            res.put("message", "Hotel recibido y guardado correctamente.");
            return ResponseEntity.ok(res);

        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al conectarse a la base de datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.status(503).body(res);

        } catch (DataAccessException err) {
            res.put("Message", "Error al consultar la base de datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.status(503).body(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    //Creamos el metodo de editar hotel
    @PutMapping("/put/{id}")
    //Utilizamos la clase reponseEntity el cual espera un valor.
    public ResponseEntity<?> editHotel(
            //Esperamos la id para buscar el que vamos a editar
            @PathVariable long id,
            /*Decimos que profilePicture va a ser parte de la solicitud del MultipartFile
             * el cual utiliza la interfaz de MultipartFile para manejar una imagen
             * la cual esta esperando el RequestPart
             */
            @RequestPart("profilePicture") MultipartFile profilePicture,
            /*Se ejecutan las validaciones que definimos en HotelDTO, con ModelAttribute indicamos que el parametro hotel
             * debe ser enlazado con los datos del formulario en la solicitud del multipart.
             * 
             */
            @Valid @ModelAttribute HotelSaveDTO hotel,
            /* Es una interfaz que se usa para capturar y manejar los errores de validación que pueden ocurrir 
            durante el proceso de enlace de datos.  */
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

    //Se crea el metodo de eliminar hotel.
    @DeleteMapping("/delete/{id}")
    // Se utiliza la clase response entity con la clase Hash map para devolver un String y un objeto.
    public ResponseEntity<Map<String, Object>> deleteHotel(
            // Se espera el id para que se pueda buscar el que se va a eliminar.    
            @PathVariable long id) {

        // se crea el hashMap que vamos a utilizar.
        Map<String, Object> res = new HashMap<>();
        try {
            //Buscamos el id que vamos a eliminar
            Hotel hotel = hotelService.findHotel(id);
            //Creamos la validacion que verifique si encontro el id.
            if (hotel == null) {
                //Modificamos el res para que nos muestre este mensaje junto a un error Http 
                res.put("message", "No se encontró el hotel con el ID especificado.");
                // El response entity retorna un Http no encontrado.
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            //Eliminamos el hotel que encontramos
            hotelService.deleteHotel(hotel);
            res.put("message", "Hotel eliminado correctamente.");
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            //Utilizamos la excepcion para ubicar un error a la hora de eliminar.
            res.put("message", "Error al intentar eliminar el hotel.");
            res.put("error", e.getMessage());
            res.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }


}
