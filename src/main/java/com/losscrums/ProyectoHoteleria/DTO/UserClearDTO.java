package com.losscrums.ProyectoHoteleria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//Creamos clase que nos sirve para filtar los datos del listar en reservacion.
public class UserClearDTO {

    private String name;
    private String surname;
    private String username;

}
