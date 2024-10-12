package com.udea.vueloudea.service;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(long id_role) {
        return roleRepository.findById(id_role);
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }
}

