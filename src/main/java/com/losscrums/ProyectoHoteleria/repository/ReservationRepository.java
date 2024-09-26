package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    //Crear el listar de reservacion que liste por id de usuario.
    List<Reservation> findByUser(User user);

    //Crear el listar de reservacion que liste por id de habitacion.
    List<Reservation> findByRoom(Room room);
}

