package com.project.library.repository;

import com.project.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
