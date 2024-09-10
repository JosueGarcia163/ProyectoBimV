package main.java.com.harlinpalacios.futecaManager.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.harlinpalacios.futecaManager.configs.Cloudinary;

@Service
public class CloudinaryService {

    @AutoWired 
    private Cloudinary cloudinary;
    public Map<String, Object> uploadProfilePinture(MultiparFile file, String folder) throws IOException{
            String originalFilename = file.getOriginalFilename();
    
        if(originalFilename == null || originalFilename.isEmpty()){
            throw new IllegalArgumentException("El archivo no puede ser nulo o estar vacio");
        }else{
            //Desfragmentar el nombre y extencion para crear un archivo con nombre nuevo
            String newName = originalFilename.substring(0,originalFilename.lastIndexOf('.'));
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String finalName = newName + "_" + timestamp;

            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap(
                "folder", folder,
                "public_id", finalName
            ));
    }
    }
}