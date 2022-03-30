package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.Technology;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechnologiesRepository extends CrudRepository<Technology, Integer> {
    @Override
    List<Technology> findAll();
}
