package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id_user) {
        return userRepository.findById(id_user).orElseThrow(() -> new IllegalArgumentException("User not found"));
        }


    public User createUser(@Argument String name, @Argument String email, @Argument String password,
                           @Argument String address, @Argument String document_number, @Argument Role role) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setDocument_number(document_number);
        user.setRole(role);
        return userRepository.save(user);
    }

}
