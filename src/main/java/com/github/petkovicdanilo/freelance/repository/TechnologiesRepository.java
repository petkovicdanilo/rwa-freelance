package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechnologiesRepository extends CrudRepository<TechnologyEntity, Integer> {
    @Override
    List<TechnologyEntity> findAll();
}
