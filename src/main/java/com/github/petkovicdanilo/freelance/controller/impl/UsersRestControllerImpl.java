package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.UsersRestController;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserSaveDto;
import com.github.petkovicdanilo.freelance.model.api.user.UsersSearchOptions;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Users")
@RequiredArgsConstructor
public class UsersRestControllerImpl implements UsersRestController {
    private final UsersService usersService;

    @Override
    public Page<UserDto> getUsers(UsersSearchOptions usersSearchOptions) {
        return usersService.getAll(usersSearchOptions);
    }

    @Override
    public UserDto getUser(int id) throws ResourceNotFoundException {
        return usersService.getOne(id);
    }

    @Override
    public UserDto saveUser(UserSaveDto user) throws UniqueViolationException {
        return usersService.save(user);
    }

    @Override
    public UserDto updateUser(int id, UserSaveDto updatedUser) throws ResourceNotFoundException, UniqueViolationException {
        return usersService.update(id, updatedUser);
    }

    @Override
    public void removeUser(int id) throws ResourceNotFoundException {
        usersService.remove(id);
    }
}
