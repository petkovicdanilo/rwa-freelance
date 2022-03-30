package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobsRepository extends CrudRepository<Job, Integer> {
    @Override
    List<Job> findAll();
}
