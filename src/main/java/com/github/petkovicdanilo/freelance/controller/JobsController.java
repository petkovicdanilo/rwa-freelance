package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.model.Job;
import com.github.petkovicdanilo.freelance.service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private final JobsService jobsService;

    @GetMapping()
    public List<Job> getJobs() {
        return jobsService.getAll();
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable int id) {
        return jobsService.getOne(id);
    }

    @PostMapping()
    public Job saveJob(@RequestBody Job job) {
        return jobsService.save(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job updatedJob) {
        return jobsService.update(id, updatedJob);
    }

    @DeleteMapping("/{id}")
    public Job remove(@PathVariable int id) {
        return jobsService.remove(id);
    }
}
