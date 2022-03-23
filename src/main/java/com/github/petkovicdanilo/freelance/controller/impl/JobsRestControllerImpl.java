package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.JobsRestController;
import com.github.petkovicdanilo.freelance.model.Job;
import com.github.petkovicdanilo.freelance.service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobsRestControllerImpl implements JobsRestController {

    private final JobsService jobsService;

    public List<Job> getJobs() {
        return jobsService.getAll();
    }

    public Job getJob(int id) {
        return jobsService.getOne(id);
    }

    public Job saveJob(Job job) {
        return jobsService.save(job);
    }

    public Job updateJob(int id, Job updatedJob) {
        return jobsService.update(id, updatedJob);
    }

    public Job remove(int id) {
        return jobsService.remove(id);
    }
}
