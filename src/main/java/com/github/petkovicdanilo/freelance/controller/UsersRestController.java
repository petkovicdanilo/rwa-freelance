package com.github.petkovicdanilo.freelance.controller;


import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersRestController {
    @GetMapping()
    List<UserDto> getUsers();

    @GetMapping("/{id}")
    UserDto getUser(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    UserDto saveUser(@RequestBody UserSaveDto user) throws UniqueViolationException;

    @PutMapping("/{id}")
    UserDto updateUser(@PathVariable int id, @RequestBody UserSaveDto updatedUser) throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeUser(@PathVariable int id) throws ResourceNotFoundException;
}