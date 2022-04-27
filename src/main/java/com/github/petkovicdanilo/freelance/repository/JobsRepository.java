package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobsRepository extends JpaRepository<JobEntity, Integer>, JpaSpecificationExecutor<JobEntity> {
    @Override
    List<JobEntity> findAll();

    List<JobEntity> findAllByOrderByPriceDesc();

    List<JobEntity> findTop2ByOrderByPriceDesc();

    @Query("SELECT j FROM JobEntity j WHERE j.price >= :minPrice")
    List<JobEntity> findAllWithMinPrice(double minPrice);
}
