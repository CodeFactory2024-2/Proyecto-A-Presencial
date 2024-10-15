package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.model.UserF;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserF> findUsers() {
        return userRepository.findAll();
    }

    public UserF searchUsersById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

    public UserF createUser( @Argument String name, @Argument String email, @Argument String password,
                            @Argument String address, @Argument String document_number, @Argument Role role) {

        UserF user = new UserF();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setDocument_number(document_number);
        user.setRole(role);
        return userRepository.save(user);
    }
}
