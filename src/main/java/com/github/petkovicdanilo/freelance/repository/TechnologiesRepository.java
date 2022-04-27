package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TechnologiesRepository extends CrudRepository<TechnologyEntity, Integer> {
    @Override
    List<TechnologyEntity> findAll();

    @Override
    Set<TechnologyEntity> findAllById(Iterable<Integer> ids);
}
