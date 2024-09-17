package com.losscrums.ProyectoHoteleria.repository;


//The extends is done to extend the entities and the data type of their PK
public interface UserRepository extends JpaRepository<User, Long>{

    //This is used to do custom searches
    public User findByUsername(String username);

}
