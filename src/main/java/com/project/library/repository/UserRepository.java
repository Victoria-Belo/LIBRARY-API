package com.project.library.repository;

import com.project.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    boolean existsByEmail(String email);
}
