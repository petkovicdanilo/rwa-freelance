package com.github.petkovicdanilo.freelance;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobsController {

    private List<Job> jobs = Arrays.asList(new Job(1, "job 1"), new Job(2, "job 2"), new Job(3, "job 3"));

    @GetMapping()
    public List<Job> getAll() {
        return jobs;
    }

    @GetMapping("/{id}")
    public Job getOne(@PathVariable int id) {
        return jobs.get(id - 1);
    }
}
