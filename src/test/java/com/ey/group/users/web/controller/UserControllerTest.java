package com.ey.group.users.web.controller;

import com.ey.group.users.persistense.entity.Phone;
import com.ey.group.users.persistense.entity.User;
import com.ey.group.users.persistense.UserRepository;
import com.ey.group.users.service.UserService;
import com.ey.group.users.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private String NUMBER = "123456";
    private String CITYCODE = "1";
    private String COUNTRYCODE = "57";
    private String UUID = "fa44bb19-a4e7-4597-bdb9-be67eab8eb57";

    @Test
    void createUser() throws RuntimeException {
        UserDto userDto = new UserDto();
        userDto.setName("Juan Rodriguez");
        userDto.setEmail("juan@rodriguez.org");
        userDto.setPassword("hunter2");

        Phone phone = new Phone();
        phone.setNumber(NUMBER);
        phone.setCitycode(CITYCODE);
        phone.setCountrycode(COUNTRYCODE);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        userDto.setPhones(phones);

        when(userService.createUser(userDto)).thenReturn(Optional.of(new User()));

        ResponseEntity<Object> responseEntity = userController.createUser(userDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");

        Phone phone = new Phone();
        phone.setNumber(NUMBER);
        phone.setCitycode(CITYCODE);
        phone.setCountrycode(COUNTRYCODE);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user.setPhones(phones);
        userList.add(user);
        when(userService.findAll()).thenReturn(Optional.of(userList));

        ResponseEntity<Optional<List<User>>> responseEntity = userController.getAllUser();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody().orElse(null));
    }

    @Test
    void getUserByEmail() {
        String userEmail = "juan@rodriguez.org";

        User user = new User();
        user.setId(java.util.UUID.fromString(UUID));
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumber(NUMBER);
        phone.setCitycode(CITYCODE);
        phone.setCountrycode(COUNTRYCODE);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user.setPhones(phones);

        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));

        ResponseEntity<Optional<Object>> responseEntity = userController.getUserByEmail(userEmail);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isPresent());
    }

}