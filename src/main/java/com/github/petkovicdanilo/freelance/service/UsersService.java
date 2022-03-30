package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public User getOne(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return usersRepository.save(user);
    }

    public User update(int id, User updatedUser) {
        User user = usersRepository.findById(id).orElse(null);

        user.setId(updatedUser.getId());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setGender(updatedUser.getGender());

        return usersRepository.save(user);
    }

    public User remove(int id) {
        User user = usersRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        }

        usersRepository.deleteById(id);
        return user;
    }
}
