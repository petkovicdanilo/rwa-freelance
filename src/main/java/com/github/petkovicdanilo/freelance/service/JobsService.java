package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.Job;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobsRepository;

    public Job getOne(int id) throws ResourceNotFoundException {
        return jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));
    }

    public List<Job> getAll(Double minPrice) {
        if(minPrice != null) {
            return jobsRepository.findAllWithMinPrice(minPrice);
        }

        return jobsRepository.findAll();
    }

    public Job save(Job job) {
        return jobsRepository.save(job);
    }

    public Job update(int id, Job updatedJob) throws ResourceNotFoundException {
        Job job = jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));

        job.setId(updatedJob.getId());
        job.setDescription(updatedJob.getDescription());

        return jobsRepository.save(job);
    }

    public void remove(int id) throws ResourceNotFoundException {
        if (!jobsRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.JOB);
        }

        jobsRepository.deleteById(id);
    }
}
