package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.losscrums.ProyectoHoteleria.model.Servicio;

// ServicioRepository es la interfaz que extiende JpaRepository
// para realizar operaciones CRUD en la entidad Servicio.
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}
