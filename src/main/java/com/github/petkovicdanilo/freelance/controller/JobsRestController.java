package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.Job;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public interface JobsRestController {
    @GetMapping()
    @Operation(description = "Get all jobs", summary = "Get all jobs")
    List<Job> getJobs(@RequestParam(name = "min-price", required = false) Double minPrice);

    @GetMapping("/{id}")
    Job getJob(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    Job saveJob(@RequestBody Job job);

    @PutMapping("/{id}")
    Job updateJob(@PathVariable int id, @RequestBody Job updatedJob) throws ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeJob(@PathVariable int id) throws ResourceNotFoundException;
}
