package com.udea.vueloudea.controller;
import com.udea.vueloudea.model.AuthRequest;
import com.udea.vueloudea.model.AuthResponse;
import com.udea.vueloudea.model.RegisterRequest;
import com.udea.vueloudea.security.JWTutil;
import com.udea.vueloudea.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthService authService;

    @Autowired
    private JWTutil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @MutationMapping
    public AuthResponse register(@Argument String name,@Argument String email, @Argument String  password){
        try {
            RegisterRequest request = new RegisterRequest(name, email, password);
            return authService.register(request);
        }catch (Exception e){
            return new AuthResponse(null, "Error al registrar el usuario");
        }
    }

    @MutationMapping
    public AuthResponse authenticate(@Argument String email, @Argument String  password) {
        try {
            return authService.authenticate(email, password);
        } catch (Exception e) {

            return new AuthResponse(null, "Error de autenticación");
        }
    }
}

