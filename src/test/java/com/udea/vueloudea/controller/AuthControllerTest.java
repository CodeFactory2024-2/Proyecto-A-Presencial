package com.udea.vueloudea.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.udea.vueloudea.service.AuthService;
import com.udea.vueloudea.model.AuthResponse;
import com.udea.vueloudea.model.RegisterRequest;

public class AuthControllerTest{

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterSuccess() {
        // Arrange
        RegisterRequest request = new RegisterRequest("John", "john@example.com", "password123");
        AuthResponse expectedResponse = new AuthResponse("token", null);
        when(authService.register(request)).thenReturn(expectedResponse);

        // Act
        AuthResponse response = authController.register("John", "john@example.com", "password123");

        // Assert
        assertEquals(expectedResponse, response);
        verify(authService, times(1)).register(request);
    }

    @Test
    public void testRegisterException() {
        // Arrange
        when(authService.register(any())).thenThrow(new RuntimeException("Error"));

        // Act
        AuthResponse response = authController.register("John", "john@example.com", "password123");

        // Assert
        assertNull(response.getToken());
        assertEquals("", response.getMessage());
    }

    @Test
    public void testAuthenticateSuccess() {
        // Arrange
        AuthResponse expectedResponse = new AuthResponse("token", null);
        when(authService.authenticate("john@example.com", "password123")).thenReturn(expectedResponse);

        // Act
        AuthResponse response = authController.authenticate("john@example.com", "password123");

        // Assert
        assertEquals(expectedResponse, response);
        verify(authService, times(1)).authenticate("john@example.com", "password123");
    }

    @Test
    public void testAuthenticateException() {
        // Arrange
        when(authService.authenticate(any(), any())).thenThrow(new RuntimeException("Error"));

        // Act
        AuthResponse response = authController.authenticate("john@example.com", "wrongpassword");

        // Assert
        assertNull(response.getToken());
        assertEquals("Error de autenticación", response.getMessage());
    }
}
