package com.ey.group.users.service;

import com.ey.group.users.persistense.entity.User;
import com.ey.group.users.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceImpl {

    Optional<User> createUser(UserDto userDto);
    Optional<User> updateUser(UserDto userDto);

    Optional<User> findByEmail(String email);
    Optional<List<User>> findAll();

}
