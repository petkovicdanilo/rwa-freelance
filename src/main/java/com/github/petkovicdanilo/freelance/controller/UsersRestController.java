package com.github.petkovicdanilo.freelance.controller;


import com.github.petkovicdanilo.freelance.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersRestController {
    @GetMapping()
    List<User> getUsers();

    @GetMapping("/{id}")
    User getUser(@PathVariable int id);

    @PostMapping()
    User saveUser(@RequestBody User user);

    @PutMapping("/{id}")
    User updateUser(@PathVariable int id, @RequestBody User updatedUser);

    @DeleteMapping("/{id}")
    User removeUser(@PathVariable int id);
}