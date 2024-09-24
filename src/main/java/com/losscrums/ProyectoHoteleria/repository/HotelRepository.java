package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Hotel;
import com.losscrums.ProyectoHoteleria.model.Reservation;

public interface  HotelRepository extends JpaRepository<Hotel, Long>{
    List<Hotel> findByReservation(Reservation reservation);

    //Creamos metodo que busque por medio de nombre y nos de una lista.
    List<Hotel> findByName(String name);

    /*Método para obtener los hoteles ordenados por nameCounter en orden descendente
        findAll nos dice que encontrara todas las entidades de tipo hotel,
        OrderBy nos dicen por que variable sera ordenada en este caso NameCounter,
        y Desc nos quiere decir que va a ordenarse de forma Descendiente osea 
        de mayor a menor.
    */ 
    List<Hotel> findAllByOrderByNameCounterDesc();
}