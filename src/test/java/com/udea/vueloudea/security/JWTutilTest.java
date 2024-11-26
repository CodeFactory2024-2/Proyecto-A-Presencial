package com.udea.vueloudea.security;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.udea.vueloudea.model.User;
import com.udea.vueloudea.model.Role;

    public class JWTutilTest{

        private final JWTutil jwtUtil = new JWTutil();

        @Test
        public void testGenerateToken() {
            // Arrange
            Role role = new Role(1L, "ROLE_USER");
            User user = User.builder()
                    .id_user(1)
                    .name("John")
                    .email("john@example.com")
                    .password("password123")
                    .address("123 Street")
                    .document_number("123456789")
                    .role(role)
                    .build();

            // Act
            String token = JWTutil.generateToken(user);

            // Assert
            assertNotNull(token);
            assertFalse(token.isEmpty());
        }

        @Test
        public void testExtractUsername() {
            // Arrange
            Role role = new Role(1L, "ROLE_USER");
            User user = User.builder()
                    .id_user(1)
                    .name("John")
                    .email("john@example.com")
                    .password("password123")
                    .address("123 Street")
                    .document_number("123456789")
                    .role(role)
                    .build();

            String token = JWTutil.generateToken(user);

            // Act
            String username = jwtUtil.extractUsername(token);

            // Assert
            assertEquals(user.getEmail(), username);
        }

        @Test
        public void testIsTokenValid() {
            // Arrange
            Role role = new Role(1L, "ROLE_USER");
            User user = User.builder()
                    .id_user(1)
                    .name("John")
                    .email("john@example.com")
                    .password("password123")
                    .address("123 Street")
                    .document_number("123456789")
                    .role(role)
                    .build();

            String token = JWTutil.generateToken(user);

            // Act
            boolean isValid = jwtUtil.isTokenValid(token, user.getEmail());

            // Assert
            assertTrue(isValid);
        }

        @Test
        public void testIsTokenInvalid() {
            // Arrange
            Role role = new Role(1L, "ROLE_USER");
            User user = User.builder()
                    .id_user(1)
                    .name("John")
                    .email("john@example.com")
                    .password("password123")
                    .address("123 Street")
                    .document_number("123456789")
                    .role(role)
                    .build();

            String token = JWTutil.generateToken(user);

            // Act
            boolean isValid = jwtUtil.isTokenValid(token, "wrong@example.com");

            // Assert
            assertFalse(isValid);
        }
    }
