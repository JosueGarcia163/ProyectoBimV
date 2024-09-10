package com.losscrums.ProyectoHoteleria.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

//Se agrego la anotacion @Service para la logica de negocio
@Service
public class CloudinaryService {

    //La anotacion @Autowired nos proporciona un control al momento de inyectar dependencias o instancias en el contexto String
    @Autowired
    private Cloudinary cloudinary;
    public Map<String, Object> uploadProfilePinture(MultipartFile file, String filder) throws IOException{
            String originalFilename = file.getOriginalFilename();
    
        if(originalFilename == null || originalFilename.isEmpty()){
            throw new IllegalArgumentException("El archivo no puede ser nulo o estar vacio");
        }else{
            //Se desfragmenta el nombre y extencion para crear un documento con un nuevo nombre
            String newName = originalFilename.substring(0,originalFilename.lastIndexOf('.'));
            String timestamp = new SimpleDateFormat("yyyy").format(new Date());
            String finalName = newName + "_" + timestamp;

            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap(
                "folder" , folder,
                "public_id", finalName
            ));
    }
    }
}