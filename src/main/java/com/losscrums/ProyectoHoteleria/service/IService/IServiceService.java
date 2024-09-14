package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Services;

// IServicio define los métodos para gestionar servicios en el sistema de hotelería.
// Incluye operaciones para listar, buscar, guardar y eliminar servicios.
public interface IServiceService {

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
    Services saveService(Services service);

    // Elimina un servicio del sistema por su ID.
    // @param idService el identificador único del servicio a eliminar.
    void deleteService(Long idService);
}
