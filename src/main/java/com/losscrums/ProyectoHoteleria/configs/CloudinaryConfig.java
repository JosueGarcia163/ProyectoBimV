package com.losscrums.ProyectoHoteleria.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
//Configuracion de Cloudinary con las respectivas credenciales.
@Bean
    Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", "dkmmydkxt", "api_key", 
        "485867633652377", "api_secret", "5HHdvXFbh9EX9uuQqo1-5fAO6a4"));

    }
}
