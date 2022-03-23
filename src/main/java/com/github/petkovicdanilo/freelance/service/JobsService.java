package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobsService {
    private List<Job> jobs;

    public JobsService() {
        jobs = new ArrayList<>();

        jobs.add(new Job(1, "job 1"));
        jobs.add(new Job(2, "job 2"));
        jobs.add(new Job(3, "job 3"));
    }

    public Job getOne(int id) {
        return jobs.get(id - 1);
    }

    public List<Job> getAll() {
        return jobs;
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
