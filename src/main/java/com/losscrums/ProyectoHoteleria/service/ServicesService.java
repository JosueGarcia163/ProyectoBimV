package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.DTO.ServiceResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ServiceSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Event;
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
    ServicesRepository serviceRepository;

    @Autowired
    EventService eventService;

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
    public Services saveServices(ServiceSaveDTO serviceDTO) {
        try {
            Event event = eventService.findEvent(serviceDTO.getEventId());
            Services services = new Services(
                    null,
                    serviceDTO.getTypeService(),
                    serviceDTO.getCapacity(),
                    serviceDTO.getCost(),
                    serviceDTO.getDescription(),
                    event
            );
            return serviceRepository.save(services);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al ingresar un dato", err);
        }
    }

    // Elimina un servicio por su ID.
    // @param idService el identificador único del servicio a eliminar.
    @Override
    public void deleteService(Services service) {
        serviceRepository.delete(service);
    }

    @Override
    public Services editServices(Long idService, ServiceSaveDTO serviceDTO) {
        try {
            Services existingService = serviceRepository.findById(idService)
                    .orElseThrow(()
                            -> new IllegalArgumentException("Servicio no encontrado con el ID: " + idService));

            existingService.setTypeService(serviceDTO.getTypeService());
            existingService.setCapacity(serviceDTO.getCapacity());
            existingService.setCost(serviceDTO.getCost());
            existingService.setDescription(serviceDTO.getDescription());

            Event event = eventService.findEvent(serviceDTO.getEventId());
            existingService.setEvent(event);

            return serviceRepository.save(existingService);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al editar el servicio", err);
        }
    }

    @Override
    public List<ServiceResponseDTO> getServiceforEvent(Long eventId) {
        Event event = eventService.findEvent(eventId);
        List<Services> serviceses = serviceRepository.findByEvent(event);
        return serviceses.stream()
                .map(services -> new ServiceResponseDTO(
                services.getIdService(),
                services.getTypeService(),
                services.getCapacity(),
                services.getCost(),
                services.getDescription(),
                services.getEvent()
        )).collect(Collectors.toList());
    }
}
