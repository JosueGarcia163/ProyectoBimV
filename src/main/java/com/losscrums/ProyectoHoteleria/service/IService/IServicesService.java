package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.DTO.ServiceResponseDTO;
import com.losscrums.ProyectoHoteleria.DTO.ServiceSaveDTO;
import com.losscrums.ProyectoHoteleria.model.Services;

// IServicesService define los métodos para gestionar servicios en el sistema de hotelería.
// Incluye operaciones para listar, buscar, guardar y eliminar servicios.
public interface IServicesService {

    // Obtiene una lista de todos los servicios.
    // @return una lista de objetos Services.
    List<Services> listServices();

    // Encuentra un servicio por su ID.
    // @param idService el identificador único del servicio.
    // @return el objeto Services correspondiente al ID, o null si no se encuentra.
    Services findService(Long idService);

    // Guarda o actualiza un servicio en el sistema.
    // @param serviceDTO el objeto ServiceSaveDTO que contiene los datos del servicio a guardar.
    // @return el objeto Services guardado o actualizado.
    Services saveServices(ServiceSaveDTO serviceDTO);

    // Elimina un servicio del sistema por su ID.
    // @param service el objeto Services que se desea eliminar.
    public void deleteService(Services service);

    // Método para listar servicios asociados a un evento específico.
    // @param eventId el identificador único del evento.
    // @return una lista de objetos ServiceResponseDTO relacionados con el evento.
    List<ServiceResponseDTO> getServiceforEvent(Long eventId);

    // Edita un servicio existente.
    // @param idService el identificador único del servicio a editar.
    // @param serviceDTO el objeto ServiceSaveDTO con los nuevos datos del servicio.
    // @return el objeto Services actualizado.
    Services editServices(Long idService, ServiceSaveDTO serviceDTO);
}
