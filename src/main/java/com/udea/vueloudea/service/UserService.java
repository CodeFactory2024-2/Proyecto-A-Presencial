package com.udea.vueloudea.service;

import com.udea.vueloudea.repository.UserRepository;
import com.udea.vueloudea.model.UserF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserF> findUsers() {
        return userRepository.findAll();
    }

    public Optional<UserF> findUserById(long id_user) {
            return  userRepository.findById(id_user);
        }

    public void createUser( UserF user) {
        userRepository.save(user);
    }
}
