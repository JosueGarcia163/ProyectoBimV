package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Hotel;

public interface  HotelRepository extends JpaRepository<Hotel, Long>{

}
