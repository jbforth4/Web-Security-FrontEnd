package com.charter.provisioning.activationapi.clortho.repository;

import com.charter.provisioning.activationapi.clortho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

