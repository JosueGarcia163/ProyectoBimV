package com.losscrums.ProyectoHoteleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.losscrums.ProyectoHoteleria.model.User;

//The extends is done to extend the entities and the data type of their PK
public interface UserRepository extends JpaRepository<User, Long>{

    //This is used to do custom searches
    public User findByUsername(String username);

}