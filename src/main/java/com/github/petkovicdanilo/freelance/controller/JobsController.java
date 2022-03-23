package com.github.petkovicdanilo.freelance;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private List<Job> jobs;

    public JobsController() {
        this.jobs = new ArrayList<>();

        this.jobs.add(new Job(1, "job 1"));
        this.jobs.add(new Job(2, "job 2"));
        this.jobs.add(new Job(3, "job 3"));
    }


    @GetMapping()
    public List<Job> getAll() {
        return jobs;
    }

    @GetMapping("/{id}")
    public Job getOne(@PathVariable int id) {
        return jobs.get(id - 1);
    }

    @PostMapping()
    public Job create(@RequestBody Job job) {
        jobs.add(job);
        return job;
    }

    @PutMapping("/{id}")
    public Job update(@RequestBody Job updatedJob, @PathVariable int id) {
        Job job = jobs.get(id - 1);
        job.setId(updatedJob.getId());
        job.setDescription(updatedJob.getDescription());

        return job;
    }

    @DeleteMapping("/{id}")
    public Job remove(@PathVariable int id) {
        Job job = jobs.get(id - 1);
        jobs.remove(id - 1);

        return job;
    }
}
