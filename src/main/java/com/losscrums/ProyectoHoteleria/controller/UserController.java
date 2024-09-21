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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.losscrums.ProyectoHoteleria.DTO.UserLoginDTO;
import com.losscrums.ProyectoHoteleria.DTO.UserSaveDTO;
import com.losscrums.ProyectoHoteleria.model.User;
import com.losscrums.ProyectoHoteleria.service.CloudinaryService;
import com.losscrums.ProyectoHoteleria.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hoteleria/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<?> listUser(){
        Map<String, Object> res = new HashMap<>();
        try{
            return ResponseEntity.ok().body(userService.listUsers());
        } catch (CannotCreateTransactionException err) {
            res.put("message", "Error al momento de conectarse a la BD");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
            //Error de consulta a la BD
        } catch (DataAccessException err) {
            res.put("message", "Error al momento de consultar a la base de datos");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
            //Error general o genérico
        } catch (Exception err) {
            res.put("message", "Error general al obtener los datos");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> addUser(
        @RequestPart("personalImage") MultipartFile personalImage,
        @Valid @ModelAttribute UserSaveDTO user,
        BindingResult result
    ){
        Map<String, Object> res = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());
            res.put("message", "Error con las validaciones, por favor ingresa todos los campos");
            res.put("Errors", errors);
            return ResponseEntity.badRequest().body(res);
        }
        try {
            Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(personalImage, "profileHoteleria");
            String img = uploadResult.get("url").toString();
            Long idUser = null;
            User newUser = new User(
                idUser,
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getNit(),
                img
            );
            userService.saveUser(newUser);
            res.put("message", "Usuario guardado correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception err) {
            res.put("message", "Error al guardar el usuario, intente de nuevo más tarde");
            res.put("error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user){
        Map<String, Object> res = new HashMap<>();
        try {
            System.out.println(user.getPassword());
            if(userService.login(user.getUsername(), user.getPassword())){
                res.put("message", "Usuario logeado correctamente");
                return ResponseEntity.ok(res);
            }else{
                res.put("message", "Credenciales incorrectas, verifica la contraseña o email");
                return ResponseEntity.status(401).body(res);
            }
        } catch (Exception err) {
            res.put("message", "Error general al iniciar sesión");
            res.put("error", err);
            return ResponseEntity.internalServerError().body(res);
        }
    }
    
}
