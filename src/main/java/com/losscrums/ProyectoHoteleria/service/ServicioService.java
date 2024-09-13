package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.losscrums.ProyectoHoteleria.model.Servicio;
import com.losscrums.ProyectoHoteleria.repository.ServicioRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IServicioService;

// ServicioService es la clase que implementa la interfaz IServicio
// para gestionar las operaciones relacionadas con los servicios,
// utilizando el repositorio ServicioRepository.
@Service
public class ServicioService implements IServicioService {

    // Inyección del repositorio de servicios.
    @Autowired
    private ServicioRepository servicioRepository;

    // Retorna una lista de todos los servicios.
    // @return una lista de objetos Servicio.
    @Override
    public List<Servicio> listServices() {
        return servicioRepository.findAll();
    }

    // Busca un servicio por su ID.
    // @param idService el identificador único del servicio.
    // @return el objeto Servicio correspondiente al ID, o null si no se encuentra.
    @Override
    public Servicio findService(Long idService) {
        return servicioRepository.findById(idService).orElse(null);
    }

    // Guarda o actualiza un servicio en la base de datos.
    // @param servicio el objeto Servicio a guardar o actualizar.
    // @return el objeto Servicio guardado o actualizado.
    @Override
    public Servicio saveService(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    // Elimina un servicio por su ID.
    // @param idService el identificador único del servicio a eliminar.
    @Override
    public void deleteService(Long idService) {
        servicioRepository.deleteById(idService);
    }

}
