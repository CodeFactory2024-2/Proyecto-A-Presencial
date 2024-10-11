package com.udea.vueloudea.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;



//comentario  para probar que funciona el sonarcloud

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_role;
    private String role;
    public Role() {}

    public Role(long id_role,String role ) {
        this.role = role;
        this.id_role = id_role;
    }

    public long getId_role() {
        return id_role;
    }

    public void setId_role(long id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //IDIOMS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Role role )) return false;

        return Objects.equals(getId_role(), role.getId_role());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_role());
    }
}

