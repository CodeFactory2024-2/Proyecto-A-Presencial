package com.udea.vueloudea.controller;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.service.RoleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class RoleController {


    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @QueryMapping
    public List<Role> searchRoles(){
        return roleService.findRoles();
    }

    @QueryMapping
    public Optional<Role> searchRolesById(@Argument long id_role){
        return roleService.findRoleById(id_role);
    }

    @MutationMapping
    public Role createRole(@Argument String role){
        return roleService.createRole(role);
    }
}
