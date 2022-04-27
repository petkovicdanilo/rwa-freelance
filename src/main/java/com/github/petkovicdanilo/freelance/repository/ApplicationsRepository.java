package com.github.petkovicdanilo.freelance.repository;

import com.github.petkovicdanilo.freelance.model.entity.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepository extends CrudRepository<ApplicationEntity, Integer> {

    Optional<ApplicationEntity> findByJobIdAndCandidateId(int jobId, int candidateId);

    boolean existsByJobIdAndCandidateId(int jobId, int candidateId);

    List<ApplicationEntity> findByJobId(int jobId);
}
