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
        return new Cloudinary(ObjectUtils.asMap("cloud_name", "dfvdnu8xa", "api_key", 
        "986183813838839", "api_secret", "o-jaaZJGdcTwdHeBDPAZNZv21SE"));

    }
}
