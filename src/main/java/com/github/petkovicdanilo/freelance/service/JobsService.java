package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import com.github.petkovicdanilo.freelance.model.mapper.JobsMapper;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobsRepository;
    private final JobsMapper jobsMapper;


    private final TechnologiesRepository technologiesRepository;

    public JobDto getOne(int id) throws ResourceNotFoundException {
        JobEntity jobEntity = jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));

        return jobsMapper.toDto(jobEntity);
    }

    public List<JobDto> getAll(Double minPrice) {
        if(minPrice != null) {
            return jobsRepository.findAllWithMinPrice(minPrice)
                    .stream()
                    .map(jobsMapper::toDto)
                    .collect(Collectors.toList());
        }

        return jobsRepository.findAll()
                .stream()
                .map(jobsMapper::toDto)
                .collect(Collectors.toList());
    }

    public JobDto save(JobSaveDto job) {
        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(job.getTechnologyIds());

        JobEntity jobEntity = jobsMapper.toEntity(job);
        jobEntity.setTechnologies(technologies);

        jobEntity = jobsRepository.save(jobEntity);

        return jobsMapper.toDto(jobEntity);
    }

    public JobDto update(int id, JobSaveDto updatedJob) throws ResourceNotFoundException {
        if(!jobsRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.JOB);
        }

        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(updatedJob.getTechnologyIds());

        JobEntity jobEntity = jobsMapper.toEntity(updatedJob);
        jobEntity.setId(id);
        jobEntity.setTechnologies(technologies);

        jobsRepository.save(jobEntity);

        return jobsMapper.toDto(jobEntity);
    }

    public void remove(int id) throws ResourceNotFoundException {
        if (!jobsRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.JOB);
        }

        jobsRepository.deleteById(id);
    }
}
