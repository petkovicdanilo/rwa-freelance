package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.mapper.JobsMapper;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobsRepository;
    private final JobsMapper jobsMapper;

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
        JobEntity jobEntity = jobsMapper.toEntity(job);

        return jobsMapper.toDto(jobsRepository.save(jobEntity));
    }

    public JobDto update(int id, JobSaveDto updatedJob) throws ResourceNotFoundException {
        if(!jobsRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.JOB);
        }

        JobEntity jobEntity = jobsMapper.toEntity(updatedJob);
        jobEntity.setId(id);

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
