package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobsService {
    private List<Job> jobs;

    public JobsService() {
        jobs = new ArrayList<>();

        jobs.add(new Job(1, "job 1", 100));
        jobs.add(new Job(2, "job 2", 200));
        jobs.add(new Job(3, "job 3", 300));
    }

    public Job getOne(int id) {
        return jobs.get(id - 1);
    }

    public List<Job> getAll(Integer minPrice) {
//        List<Job> filteredJobs = new ArrayList<>();
//        for(Job job : jobs) {
//            if(job.getPrice() >= minPrice) {
//                filteredJobs.add(job);
//            }
//        }
//
//        return filteredJobs;

        if(minPrice == null) {
            return jobs;
        }

        return jobs.stream()
                .filter(job -> job.getPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    public Job save(Job job) {
        jobs.add(job);
        return job;
    }

    public Job update(int id, Job updatedJob) {
        Job job = jobs.get(id - 1);
        job.setId(updatedJob.getId());
        job.setDescription(updatedJob.getDescription());

        return job;
    }

    public Job remove(int id) {
        Job job = jobs.get(id - 1);
        jobs.remove(id - 1);

        return job;
    }
}
