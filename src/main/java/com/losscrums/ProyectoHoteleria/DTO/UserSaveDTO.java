package com.losscrums.ProyectoHoteleria.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSaveDTO {
    @NotBlank(message = "El nombre no puede ir vacío")
    private String name;
    @NotBlank(message = "El apellido no puede ir vacío")
    private String surname;
    @NotBlank(message = "El nombre de usuario no puede ir vacío")
    private String username;
    @Email
    @NotBlank(message = "El correo no puede ir vacío")
    private String email;
    @NotBlank(message = "La password no puede ir vacía")
    private String password;
    @NotNull(message = "El NIT no puede ir vacío")
    private Long nit;
}