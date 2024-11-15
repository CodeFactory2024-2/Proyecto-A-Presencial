package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
        user.setPassword(passwordEncoder.encode(password));
        user.setAddress(address);
        user.setDocument_number(document_number);
        user.setRole(role);
        return userRepository.save(user);
    }

    public void deleteUser(long id_user) {
        userRepository.deleteById(id_user);
    }

    public User updateUser(@Argument String name,
                           @Argument String email,
                           @Argument String password,
                           @Argument String address,
                           @Argument String document_number){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (name != null){
            user.setName(name);
        }
        if (password != null){
            user.setPassword(passwordEncoder.encode(password));
        }
        if (address != null){
            user.setAddress(address);
        }
        if (document_number != null){
            user.setDocument_number(document_number);
        }
        return userRepository.save(user);

    }

}
