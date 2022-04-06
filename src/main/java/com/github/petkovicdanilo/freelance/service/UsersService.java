package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public User getOne(int id) throws ResourceNotFoundException {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));
    }

    public User save(User user) throws UniqueViolationException {
        if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        return usersRepository.save(user);
    }

    public User update(int id, User updatedUser) throws ResourceNotFoundException, UniqueViolationException {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        if(!user.getEmail().equals(updatedUser.getEmail())
                && usersRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        user.setId(updatedUser.getId());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setGender(updatedUser.getGender());

        return usersRepository.save(user);
    }

    public void remove(int id) throws ResourceNotFoundException {
        if(!usersRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.USER);
        }

        usersRepository.deleteById(id);
    }
}
