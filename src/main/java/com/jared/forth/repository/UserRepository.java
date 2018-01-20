package com.jared.forth.repository;

import com.jared.forth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

