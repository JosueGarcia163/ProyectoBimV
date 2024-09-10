package com.losscrums.ProyectoHoteleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.Habitacion;

 // HabitacionRepository es la interfaz que extiende JpaRepository
 // para realizar operaciones CRUD en la entidad Habitacion.
 // Proporciona métodos personalizados para buscar habitaciones
 // por tipo de habitación, disponibilidad y capacidad.
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

     // Encuentra una lista de habitaciones según el tipo de habitación.
     // @param roomType el tipo de habitación (por ejemplo, 'Sencilla', 'Doble', etc.)
     // @return una lista de habitaciones que coinciden con el tipo de habitación.
    List<Habitacion> findByRoomType(String roomType);

    
     // Encuentra una lista de habitaciones según su disponibilidad.
     // @param availability la disponibilidad de la habitación (true si está disponible, false si no).
     // @return una lista de habitaciones que coinciden con la disponibilidad.
    List<Habitacion> findByAvailability(Boolean availability);


    // Encuentra una lista de habitaciones según su capacidad.    
    // @param capacity la capacidad de la habitación (por ejemplo, '2 personas', '4 personas', etc.)
    // @return una lista de habitaciones que coinciden con la capacidad.
    List<Habitacion> findByCapacity(String capacity);
}
