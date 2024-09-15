package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.ServiceResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ServiceSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Services;

// IServicio define los métodos para gestionar servicios en el sistema de hotelería.
// Incluye operaciones para listar, buscar, guardar y eliminar servicios.
public interface IServicesService {

    // Obtiene una lista de todos los servicios.
    // @return una lista de objetos Servicio.
    List<Services> listServices();

    // Encuentra un servicio por su ID.
    // @param idService el identificador único del servicio.
    // @return el objeto Servicio correspondiente al ID, o null si no se encuentra.
    Services findService(Long idService);

    // Guarda o actualiza un servicio en el sistema.
    // @param servicio el objeto Servicio a guardar.
    // @return el objeto Servicio guardado o actualizado.
    Services saveServices(ServiceSaveDTO serviceDTO);

    // Elimina un servicio del sistema por su ID.
    // @param idService el identificador único del servicio a eliminar.
    public void deleteService(Services service);

    // Metodo para listar servicio por evento
    List<ServiceResponseDTO> getServiceforEvent(Long eventId);

    Services editServices(Long idService, ServiceSaveDTO serviceDTO);
}
