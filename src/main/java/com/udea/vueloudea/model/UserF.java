package com.udea.vueloudea.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @Override
    public int hashCode() {
        return Objects.hash(getId_user());
    }
}

