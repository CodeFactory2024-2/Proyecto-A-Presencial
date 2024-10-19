package com.udea.vueloudea.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.udea.vueloudea.model.Role;
import com.udea.vueloudea.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchRoles() {

        List<Role> mockRoles = Arrays.asList(
                new Role(1, "Admin"),
                new Role(2, "User")
        );

        when(roleService.findRoles()).thenReturn(mockRoles);

        List<Role> roles = roleController.searchRoles();

        assertEquals(2, roles.size());
        assertEquals("Admin", roles.get(0).getRole());
        assertEquals("User", roles.get(1).getRole());

        verify(roleService, times(1)).findRoles();
    }

    @Test
    public void testSearchRolesById() {

        Role mockRole = new Role(1, "Admin");
        when(roleService.findRoleById(1L)).thenReturn(Optional.of(mockRole));

        Optional<Role> result = roleController.searchRolesById(1L);

        assertTrue(result.isPresent());
        assertEquals("Admin", result.get().getRole());


        verify(roleService, times(1)).findRoleById(1L);
    }

    @Test
    public void testCreateRole() {

        Role newRole = new Role(3, "Manager");
        doNothing().when(roleService).createRole(any(Role.class));

        Role result = roleController.createRole(3L, "Manager");

        assertEquals(3, result.getId_role());
        assertEquals("Manager", result.getRole());

        verify(roleService, times(1)).createRole(any(Role.class));
    }
}