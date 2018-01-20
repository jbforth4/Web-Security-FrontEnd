package com.jared.forth.service;

import com.jared.forth.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    ResponseEntity addNewUser(User user);
}
