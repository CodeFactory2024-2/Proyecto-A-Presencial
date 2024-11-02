package com.udea.vueloudea.model;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String document_number;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_user());
    }


   }


