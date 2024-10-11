package com.udea.vueloudea.controller;

import com.udea.vueloudea.service.UserService;
import com.udea.vueloudea.model.UserF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5432")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String healthCheck(){
        return "PROYECTO ACTIVO.-...";
    }

    @GetMapping("/searchAll")
    public List<UserF> searchUsers(){
        return userService.findUsers();
    }

    @GetMapping("/search")
    public Optional<UserF> searchUser(@RequestParam (value = "id_user", required = true)long id_user) {
        return userService.findUserById(id_user);
    }

    @PostMapping("/create")
    public UserF createUser(@RequestParam (value = "id_user")int id_user,
                            @RequestParam (value = "name")String name,
                            @RequestParam (value = "email")String email,
                            @RequestParam (value = "password")String password,
                            @RequestParam (value = "address")String address,
                            @RequestParam (value = "document_number")String document_number,
                            @RequestParam (value = "role")String role) {

        UserF user = new UserF(id_user, name, email, password, address, document_number, role);

        userService.createUser(user);
        return  user;
    }



}
