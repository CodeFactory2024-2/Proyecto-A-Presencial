package com.udea.vueloudea.repository;

import com.udea.vueloudea.model.UserF;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserF, Long> {
    Optional<UserF> findOneByEmail(String email);
}
