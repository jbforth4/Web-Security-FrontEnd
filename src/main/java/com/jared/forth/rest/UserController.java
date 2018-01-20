package com.jared.forth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jared.forth.model.User;
import com.jared.forth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{userId}")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping(value= "/user/all")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @RequestMapping("/whoami")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @PostMapping(value = "/addNewUser", consumes = "application/json")
    public void addNewUser(@RequestBody String json) {
        log.info("user: " + json);
        if(!json.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping();
            mapper.findAndRegisterModules();
            User user = null;
            try {
                user = mapper.readValue(json, User.class);
                user.setPassword(user.hashPassword(user.getPassword()));
                log.info("after mapper: " + user.toString());
            } catch (IOException e) {
                log.error("Error saving new user" + e.getMessage());
            }
            userService.addNewUser(user);
        }
        else {
            log.info("Error adding new user");
        }
    }
}
