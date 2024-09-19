package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;

import com.losscrums.ProyectoHoteleria.model.User;

public interface IUserService {

    // Método para listar todos los usuarios
    public List<User> listUsers();

    // Método para buscar un usuario por su ID
    public User findUserById(Long id);

    // Método para guardar un usuario
    public User saveUser(User user);

    // Método para eliminar un usuario
    public void deleteUser(User user);

    // Método para buscar un usuario por su nombre de usuario
    public User findUserByUsername(String username);

   // public Event editUser(Long idUser, UserSaveDTO userDTO);



}
