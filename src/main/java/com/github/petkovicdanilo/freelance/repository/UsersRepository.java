package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    @Override
    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);
}
