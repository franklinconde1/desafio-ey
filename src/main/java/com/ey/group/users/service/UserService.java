package com.ey.group.users.service;

import com.ey.group.users.persistense.entity.User;
import com.ey.group.users.persistense.UserRepository;
import com.ey.group.users.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImpl{

    private final UserRepository userRepository;

    @Override
    public Optional<User> createUser(UserDto userDto) {
            if (findByEmail(userDto.getEmail()).isPresent()) {
                throw new RuntimeException("El usuario ya existe");
            }
            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setPhones(userDto.getPhones());

            return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if(userRepository == null){
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
        }catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.of(userRepository.findAll());
    }

}
