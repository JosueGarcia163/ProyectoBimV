package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Services;

// ServicioRepository es la interfaz que extiende JpaRepository
// para realizar operaciones CRUD en la entidad Servicio.
public interface ServicesRepository extends JpaRepository<Services, Long> {

}

