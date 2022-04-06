package com.github.petkovicdanilo.freelance.controller;


import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersRestController {
    @GetMapping()
    List<User> getUsers();

    @GetMapping("/{id}")
    User getUser(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    User saveUser(@RequestBody User user) throws UniqueViolationException;

    @PutMapping("/{id}")
    User updateUser(@PathVariable int id, @RequestBody User updatedUser) throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeUser(@PathVariable int id) throws ResourceNotFoundException;
}