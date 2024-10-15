package com.udea.vueloudea.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;



//comentario  para probar que funciona el sonarcloud

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_role;
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserF> user_fs;

    public Role(String role) {
        this.role = role;
    }

    // Getters y Setters


    @Override
    public int hashCode() {
        return Objects.hash(getId_role());
    }
}
