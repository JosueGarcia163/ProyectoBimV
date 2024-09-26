package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Reservation;
import com.losscrums.ProyectoHoteleria.model.Room;
import com.losscrums.ProyectoHoteleria.model.User;

// ReservationRepository es la interfaz que extiende JpaRepository
// para realizar operaciones CRUD en la entidad Reservation.
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Encuentra una lista de reservas asociadas a un usuario específico.
    // @param user el usuario del cual se desean obtener las reservas.
    // @return una lista de objetos Reservation relacionados con el usuario.
    List<Reservation> findByUser(User user);

    // Encuentra una lista de reservas asociadas a una habitación específica.
    // @param room la habitación de la cual se desean obtener las reservas.
    // @return una lista de objetos Reservation relacionados con la habitación.
    List<Reservation> findByRoom(Room room);
}
