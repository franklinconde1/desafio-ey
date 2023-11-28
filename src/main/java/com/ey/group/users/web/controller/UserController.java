package com.ey.group.users.web.controller;

import com.ey.group.users.persistense.entity.User;
import com.ey.group.users.service.UserService;
import com.ey.group.users.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        log.info("Buscar usuario por email: {}", userDto.getEmail());
        log.info("Creando usuario: {}", userDto);
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        log.info("Buscar usuario por email: {}", userDto.getEmail());
        log.info("Creando usuario: {}", userDto);
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @GetMapping("/all")
    public ResponseEntity<Optional<List<User>>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Object>> getUserByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(Optional.of(userService.findByEmail(email)));
    }

}
