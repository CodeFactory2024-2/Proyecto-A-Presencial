package com.udea.vueloudea.model;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_role;
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(String role) {
        this.role = role;
    }

    public Role(Long idRole, String role) {
        this.id_role = idRole;
        this.role = role;
    }
}