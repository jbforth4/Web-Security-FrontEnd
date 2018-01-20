package com.charter.provisioning.activationapi.clortho.service.impl;

import com.charter.provisioning.activationapi.clortho.model.User;
import com.charter.provisioning.activationapi.clortho.repository.UserRepository;
import com.charter.provisioning.activationapi.clortho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername( String username ) throws UsernameNotFoundException {
        return userRepository.findByUsername( username );
    }

    public User findById( Long id ) throws AccessDeniedException {
        return userRepository.findOne( id );
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    public ResponseEntity addNewUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("Input Successful");
    }
}
