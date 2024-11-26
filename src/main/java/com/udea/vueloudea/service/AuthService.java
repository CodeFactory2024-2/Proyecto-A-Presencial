package com.udea.vueloudea.service;

import com.udea.vueloudea.model.*;
import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.security.JWTutil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTutil jWTutil;

    private final AuthenticationManager authenticationManager;



    public AuthResponse register(RegisterRequest request){
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(new Role(2L, "USER"))
                .build();
        userRepository.save(user);
        var jwtToken = JWTutil.generateToken(user);
        return AuthResponse.builder().token(jwtToken).message("Usuario registrado exitosamente").build();
    }

    public AuthResponse authenticate(String email, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, password
                )
        );
        var user = userRepository.findByEmail(email).orElseThrow();
        var jwtToken = jWTutil.generateToken(user);
        return AuthResponse.builder().token(jwtToken).message("Usuario auktenticado exitosamente").build();
    }
}