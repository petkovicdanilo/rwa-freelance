package com.github.petkovicdanilo.freelance.controller;


import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserSaveDto;
import com.github.petkovicdanilo.freelance.model.api.user.UsersSearchOptions;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UsersRestController {
    @GetMapping()
    @Operation(description = "Get all users", summary = "Get all users")
    Page<UserDto> getUsers(@ParameterObject UsersSearchOptions usersSearchOptions);

    @GetMapping("/{id}")
    @Operation(description = "Get one user", summary = "Get one user")
    UserDto getUser(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create user", summary = "Create user")
    UserDto saveUser(@Valid @RequestBody UserSaveDto user) throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update user", summary = "Update user")
    UserDto updateUser(@PathVariable int id, @Valid @RequestBody UserSaveDto updatedUser) throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete user", summary = "Delete user")
    void removeUser(@PathVariable int id) throws ResourceNotFoundException;
}