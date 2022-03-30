package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    private final List<User> users;

    public UsersService() {
        users = new ArrayList<>();

        users.add(User.builder()
                .id(1)
                .firstName("Pera")
                .lastName("Peric")
                .email("pera.peric@example.com")
                .password("pera")
                .gender(User.Gender.MALE)
                .build());
        users.add(User.builder()
                .id(2)
                .firstName("Milica")
                .lastName("Milicevic")
                .email("milica@example.com")
                .password("milica")
                .gender(User.Gender.FEMALE)
                .build());
        users.add(User.builder()
                .id(3)
                .firstName("Vanja")
                .lastName("Vanjic")
                .email("vanja@example.com")
                .password("vanja")
                .build());
    }

    public List<User> getAll() {
        return users;
    }

    public User getOne(int id) {
        return users.get(id - 1);
    }

    public User save(User user) {
        users.add(user);

        return user;
    }

    public User update(int id, User updatedUser) {
        User user = this.getOne(id);

        user.setId(updatedUser.getId());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setGender(updatedUser.getGender());

        return user;
    }

    public User remove(int id) {
        User user = this.getOne(id);

        users.remove(id - 1);

        return user;
    }
}
