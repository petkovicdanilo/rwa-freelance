package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<UserEntity, Integer> {
    @Override
    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);
}
