package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.UsersRestController;
import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersRestControllerImpl implements UsersRestController {
    private final UsersService usersService;

    @Override
    public List<User> getUsers() {
        return usersService.getAll();
    }

    @Override
    public User getUser(int id) {
        return usersService.getOne(id);
    }

    @Override
    public User saveUser(User user) {
        return usersService.save(user);
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        return usersService.update(id, updatedUser);
    }

    @Override
    public User removeUser(int id) {
        return usersService.remove(id);
    }
}
