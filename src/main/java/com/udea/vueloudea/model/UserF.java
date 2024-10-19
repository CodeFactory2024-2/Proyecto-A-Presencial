package com.udea.vueloudea.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class UserF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String document_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserF() {
    }

    public UserF(long id_user, String name, String email, String password, String address, String document_number, Role role) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.document_number = document_number;
        this.role = role;
    }

   

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }




    @Override
    public int hashCode() {
        return Objects.hash(getId_user());
    }
}

