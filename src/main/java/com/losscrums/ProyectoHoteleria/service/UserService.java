package com.losscrums.ProyectoHoteleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.losscrums.ProyectoHoteleria.model.User;
import com.losscrums.ProyectoHoteleria.repository.UserRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IUserService;
import com.losscrums.ProyectoHoteleria.utils.BCryptSecurity;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptSecurity bCryptSecurity;

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
        if(user.getPassword() !=null){
            user.setPassword(bCryptSecurity.encodePassword(user.getPassword()));
        }
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
    public boolean login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null || !bCryptSecurity.checkPassword(password, user.getPassword())){
            return false;
        }
        return true;
    }
}

