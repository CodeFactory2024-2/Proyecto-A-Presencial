package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "https://localhost:5432")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/searchAll")
    public List<Role> searchRoles(){
        return roleService.findRoles();
    }

    @GetMapping("/search")
    public Optional<Role> searchRolesById(@RequestParam (value = "id_role", required = true)long id_role){
        return roleService.findRoleById(id_role);
    }

    @PostMapping("/create")
    public Role createRole(@RequestParam long id_role, @RequestParam String role){

        Role newrole = new Role(id_role, role);
        roleService.createRole(newrole);
        return newrole;
    }
}
