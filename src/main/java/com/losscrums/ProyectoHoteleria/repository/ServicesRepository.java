package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Event;
import com.losscrums.ProyectoHoteleria.model.Services;

// ServicesRepository es la interfaz que extiende JpaRepository
// para realizar operaciones CRUD en la entidad Services.
public interface ServicesRepository extends JpaRepository<Services, Long> {

    // Encuentra una lista de servicios asociados a un evento específico.
    // @param event el evento del cual se desean obtener los servicios.
    // @return una lista de objetos Services relacionados con el evento.
    List<Services> findByEvent(Event event);
}
