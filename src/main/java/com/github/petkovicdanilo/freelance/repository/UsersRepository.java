package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
}
