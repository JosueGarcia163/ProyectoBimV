package com.losscrums.ProyectoHoteleria.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

//importamos la configuracion de la anotacion
@Configuration
public class CloudinaryConfig {

    //Se utiliza esta anotacion @Bean para que la instancia sea registrada en un contexto String
    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name" , "",
            "api_key" , "",
            "api_secret" , ""
        ));
    }
}
