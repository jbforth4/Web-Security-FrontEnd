package com.charter.provisioning.activationapi.clortho.service;

import com.charter.provisioning.activationapi.clortho.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    ResponseEntity addNewUser(User user);
}
