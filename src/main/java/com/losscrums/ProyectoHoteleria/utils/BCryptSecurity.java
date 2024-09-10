package com.losscrums.ProyectoHoteleria.utils;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class BCryptSecurity {
    //Metodo para encriptar la password 
    //Metodo para verificar si coincide el texto plano en el hash (password encriptada)

    public String encodePassword(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray() );
    }

      public boolean checkPassword(String password, String hashedPassword){
        BCrypt.Result result =  BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
      }
}
