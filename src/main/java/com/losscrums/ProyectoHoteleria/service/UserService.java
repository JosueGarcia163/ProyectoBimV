package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.User;
import com.losscrums.ProyectoHoteleria.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        // Buscar usuario por ID, retornar null si no se encuentra
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        // Guardar o actualizar un usuario
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        // Eliminar un usuario
        userRepository.delete(user);
    }

    @Override
    public User findUserByUsername(String username) {
        // Buscar un usuario por su nombre de usuario
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        // Buscar un usuario por su correo electrónico
        return userRepository.findUserByEmail(email);
    }
}

