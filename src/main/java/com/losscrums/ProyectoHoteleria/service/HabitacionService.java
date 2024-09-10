package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.Habitacion;
import com.losscrums.ProyectoHoteleria.repository.HabitacionRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IHabitacionService;

 // HabitacionService es la clase que implementa la interfaz IHabitacionService
 // para gestionar las operaciones relacionadas con las habitaciones, utilizando
 // el repositorio HabitacionRepository.
@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

     // Retorna una lista de todas las habitaciones.
     // @return una lista de objetos Habitacion.
    @Override
    public List<Habitacion> listRoom() {
        return habitacionRepository.findAll();
    }

     // Busca una habitación por su ID.
     // @param id el identificador único de la habitación.
     // @return el objeto Habitacion correspondiente al ID, o null si no se encuentra.
    @Override
    public Habitacion findRoom(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

     // Guarda o actualiza una habitación en la base de datos.
     // @param habitacion el objeto Habitacion a guardar o actualizar.
     // @return el objeto Habitacion guardado o actualizado.
    @Override
    public Habitacion saveRoom(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

     // Elimina una habitación por su ID.
     // @param id el identificador único de la habitación a eliminar.
    @Override
    public void deleteRoom(Long id) {
        habitacionRepository.deleteById(id);
    }

}
