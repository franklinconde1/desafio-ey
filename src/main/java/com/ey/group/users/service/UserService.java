package com.ey.group.users.service;

import com.ey.group.users.persistense.entity.User;
import com.ey.group.users.persistense.UserRepository;
import com.ey.group.users.service.dto.UserDto;
import com.ey.group.users.web.controller.Util.PasswordValidator;
import com.ey.group.users.web.controller.Util.ValidarEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;

    @Override
    public Optional<User> createUser(UserDto userDto) {
        try {
            if (findByEmail(userDto.getEmail()).isPresent()) {
                throw new RuntimeException("El usuario ya existe");
            }
            if (!ValidarEmail.validarEmail(userDto.getEmail())){
                throw new RuntimeException("Formato de email incorrecto");
            }
            if(!PasswordValidator.validatePassword(userDto.getPassword())) {
                throw new RuntimeException("Formato incorrecto de password: Debe tener al menos una letra Mayuscula, letras minusculas y dos numeros");
            }
            User user = new User();
            user.setCreatedAt(LocalDateTime.now());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setPhones(userDto.getPhones());

            return Optional.of(userRepository.save(user));
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> updateUser(UserDto userDto) {
        try{
            Optional<User> existingUserOptional = findByEmail(userDto.getEmail());
            if (!existingUserOptional.isPresent()) {
                throw new RuntimeException("El usuario no existe");
            }
            if (!ValidarEmail.validarEmail(userDto.getEmail())){
                throw new RuntimeException("Formato de email incorrecto");
            }
            if(!PasswordValidator.validatePassword(userDto.getPassword())) {
                throw new RuntimeException("Formato incorrecto de password: Debe tener al menos una letra Mayuscula, letras minusculas y dos numeros");
            }
            User user = existingUserOptional.get();
            user.setCreatedAt(LocalDateTime.now());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setPhones(userDto.getPhones());

            return Optional.of(userRepository.save(user));
        }catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (userRepository == null) {
            throw new IllegalStateException("UserRepository is not initialized");
        }
        try {
            if (email == null) {
                throw new RuntimeException("El email no pude ser null");
            } else if (email.isEmpty()) {
                throw new RuntimeException("El email es requerido");
            } else {
                return userRepository.findByEmail(email);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(userRepository.findAll());
    }

}
