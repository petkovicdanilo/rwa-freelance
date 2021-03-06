package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobUpdateDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobsSearchOptions;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.model.mapper.JobsMapper;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import com.github.petkovicdanilo.freelance.repository.specification.JobsSearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobsRepository;
    private final JobsMapper jobsMapper;


    private final TechnologiesRepository technologiesRepository;

    private final UsersRepository usersRepository;

    public JobDto getOne(int id) throws ResourceNotFoundException {
        JobEntity jobEntity = jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));

        return jobsMapper.toDto(jobEntity);
    }

    public Page<JobDto> getAll(JobsSearchOptions jobsSearchOptions) {
        int page = 0;
        if(jobsSearchOptions.getPage() != null && jobsSearchOptions.getPage() > 0) {
            page = jobsSearchOptions.getPage() - 1;
        }

        int pageSize = 10;
        if(jobsSearchOptions.getPageSize() != null && jobsSearchOptions.getPageSize() > 0) {
            pageSize = jobsSearchOptions.getPageSize();
        }

        return jobsRepository
                .findAll(new JobsSearchSpecification(jobsSearchOptions), PageRequest.of(page, pageSize))
                .map(jobsMapper::toDto);
    }

    public JobDto save(JobSaveDto job) throws ResourceNotFoundException {
        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(job.getTechnologyIds());

        UserEntity employer = usersRepository.findById(job.getEmployerId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        JobEntity jobEntity = jobsMapper.toEntity(job);
        jobEntity.setEmployer(employer);
        jobEntity.setTechnologies(technologies);

        jobEntity = jobsRepository.save(jobEntity);

        return jobsMapper.toDto(jobEntity);
    }

    public JobDto update(int id, JobUpdateDto updatedJob) throws ResourceNotFoundException {
        JobEntity originalJobEntity = jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));

        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(updatedJob.getTechnologyIds());

        JobEntity jobEntity = jobsMapper.toEntity(updatedJob);
        jobEntity.setId(id);
        jobEntity.setTechnologies(technologies);
        jobEntity.setEmployer(originalJobEntity.getEmployer());

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
