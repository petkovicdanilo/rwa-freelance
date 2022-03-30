package com.github.petkovicdanilo.freelance.service;

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

    public Job getOne(int id) {
        return jobsRepository.findById(id).orElse(null);
    }

    public List<Job> getAll(Double minPrice) {
        return jobsRepository.findAll();
    }

    public Job save(Job job) {
        return jobsRepository.save(job);
    }

    public Job update(int id, Job updatedJob) {
        Job job = jobsRepository.findById(id).orElse(null);

        job.setId(updatedJob.getId());
        job.setDescription(updatedJob.getDescription());

        return jobsRepository.save(job);
    }

    public void remove(int id) {
        if (!jobsRepository.existsById(id)) {
            return;
        }

        jobsRepository.deleteById(id);
    }
}
