package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Services;
import com.losscrums.ProyectoHoteleria.repository.ServicesRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IServicesService;

// ServicioService es la clase que implementa la interfaz IServicio
// para gestionar las operaciones relacionadas con los servicios,
// utilizando el repositorio ServicioRepository.
@Service
public class ServicesService implements IServicesService {

    // Inyección del repositorio de servicios.
    @Autowired
    private ServicesRepository serviceRepository;

    // Retorna una lista de todos los servicios.
    // @return una lista de objetos Servicio.
    @Override
    public List<Services> listServices() {
        return serviceRepository.findAll();
    }

    // Busca un servicio por su ID.
    // @param idService el identificador único del servicio.
    // @return el objeto Servicio correspondiente al ID, o null si no se encuentra.
    @Override
    public Services findService(Long idService) {
        return serviceRepository.findById(idService).orElse(null);
    }

    // Guarda o actualiza un servicio en la base de datos.
    // @param servicio el objeto Servicio a guardar o actualizar.
    // @return el objeto Servicio guardado o actualizado.
    @Override
    public Services saveService(Services servicio) {
        return serviceRepository.save(servicio);
    }

    // Elimina un servicio por su ID.
    // @param idService el identificador único del servicio a eliminar.
    @Override
    public void deleteService(Services servicio) {
        serviceRepository.delete(servicio);
    }

}

