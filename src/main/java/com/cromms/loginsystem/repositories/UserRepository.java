package com.cromms.loginsystem.repositories;

import com.cromms.loginsystem.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Page<User> findByName(Pageable pageable, String name);

}
