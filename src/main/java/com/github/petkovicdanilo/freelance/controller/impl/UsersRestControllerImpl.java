package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.UsersRestController;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Users")
@RequiredArgsConstructor
public class UsersRestControllerImpl implements UsersRestController {
    private final UsersService usersService;

    @Override
    public List<User> getUsers() {
        return usersService.getAll();
    }

    @Override
    public User getUser(int id) throws ResourceNotFoundException {
        return usersService.getOne(id);
    }

    @Override
    public User saveUser(User user) throws UniqueViolationException {
        return usersService.save(user);
    }

    @Override
    public User updateUser(int id, User updatedUser) throws ResourceNotFoundException, UniqueViolationException {
        return usersService.update(id, updatedUser);
    }

    @Override
    public void removeUser(int id) throws ResourceNotFoundException {
        usersService.remove(id);
    }
}
