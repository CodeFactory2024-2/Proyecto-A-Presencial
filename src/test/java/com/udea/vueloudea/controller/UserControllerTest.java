package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.model.UserF;
import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void findUsers_shouldReturnAllUsers() {

        List<UserF> expectedUsers = Arrays.asList(new UserF(), new UserF());
        when(userRepository.findAll()).thenReturn(expectedUsers);


        List<UserF> actualUsers = userService.findUsers();

        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).findAll();
    }

    @Test
    void findUserById_shouldReturnUser_whenUserExists() {

        long userId = 1L;
        UserF expectedUser = new UserF();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));


        UserF actualUser = userService.findUserById(userId);


        assertEquals(expectedUser, actualUser);
        verify(userRepository).findById(userId);
    }

    @Test
    void findUserById_shouldThrowException_whenUserNotFound() {

        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        assertThrows(IllegalArgumentException.class, () -> userService.findUserById(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void createUser_shouldSaveAndReturnUser() {

        String name = "John Doe";
        String email = "john@example.com";
        String password = "password123";
        String address = "123 Main St";
        String documentNumber = "123456789";
        Role role = new Role(1L, "USER");

        UserF expectedUser = new UserF();
        expectedUser.setName(name);
        expectedUser.setEmail(email);
        expectedUser.setPassword(password);
        expectedUser.setAddress(address);
        expectedUser.setDocument_number(documentNumber);
        expectedUser.setRole(role);

        when(userRepository.save(any(UserF.class))).thenReturn(expectedUser);


        UserF actualUser = userService.createUser(name, email, password, address, documentNumber, role);

        assertEquals(expectedUser, actualUser);
        verify(userRepository).save(any(UserF.class));

        assertEquals(name, actualUser.getName());
        assertEquals(email, actualUser.getEmail());
        assertEquals(password, actualUser.getPassword());
        assertEquals(address, actualUser.getAddress());
        assertEquals(documentNumber, actualUser.getDocument_number());
        assertEquals(role, actualUser.getRole());
    }
}