package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobsSearchOptions;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
public interface JobsRestController {
    @GetMapping()
    @Operation(description = "Get all jobs", summary = "Get all jobs")
    Page<JobDto> getJobs(@ParameterObject JobsSearchOptions jobsSearchOptions);

    @GetMapping("/{id}")
    @Operation(description = "Get one job", summary = "Get one job")
    JobDto getJob(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create job", summary = "Create job")
    JobDto saveJob(@Valid @RequestBody JobSaveDto job);

    @PutMapping("/{id}")
    @Operation(description = "Update job", summary = "Update job")
    JobDto updateJob(@PathVariable int id, @Valid @RequestBody JobSaveDto updatedJob) throws ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete job", summary = "Delete job")
    void removeJob(@PathVariable int id) throws ResourceNotFoundException;
}
