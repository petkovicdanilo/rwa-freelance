package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobsRepository extends CrudRepository<Job, Integer> {
    @Override
    List<Job> findAll();

    List<Job> findAllByOrderByPriceDesc();

    List<Job> findTop2ByOrderByPriceDesc();

    @Query("SELECT j FROM Job j WHERE j.price >= :minPrice")
    List<Job> findAllWithMinPrice(double minPrice);
}
