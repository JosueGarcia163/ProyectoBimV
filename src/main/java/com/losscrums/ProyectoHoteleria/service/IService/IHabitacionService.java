package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.Habitacion;

// IHabitacionService define los métodos para gestionar habitaciones
// en el sistema de hotelería. Incluye operaciones para listar, buscar,
// guardar y eliminar habitaciones.
public interface IHabitacionService {

    // Obtiene una lista de todas las habitaciones.
    // @return una lista de objetos Habitacion.
    List<Habitacion> listRoom();

    // Encuentra una habitación por su ID.
    // @param id el identificador único de la habitación.
    // @return el objeto Habitacion correspondiente al ID, o null si no se encuentra.
    Habitacion findRoom(Long id);

    // Guarda o actualiza una habitación en el sistema.
    // @param habitacion el objeto Habitacion a guardar.
    // @return el objeto Habitacion guardado o actualizado.
    Habitacion saveRoom(Habitacion habitacion);

    // Elimina una habitación del sistema por su ID.
    // @param id el identificador único de la habitación a eliminar.
    void deleteRoom(Long id);
}
