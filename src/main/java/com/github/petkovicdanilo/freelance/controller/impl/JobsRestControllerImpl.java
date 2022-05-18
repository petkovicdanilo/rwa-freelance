package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.JobsRestController;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobUpdateDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobsSearchOptions;
import com.github.petkovicdanilo.freelance.service.JobsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Jobs")
@RequiredArgsConstructor
public class JobsRestControllerImpl implements JobsRestController {

    private final JobsService jobsService;

    public Page<JobDto> getJobs(JobsSearchOptions jobsSearchOptions) {
        return jobsService.getAll(jobsSearchOptions);
    }

    public JobDto getJob(int id) throws ResourceNotFoundException {
        return jobsService.getOne(id);
    }

    public JobDto saveJob(JobSaveDto job) throws ResourceNotFoundException {
        return jobsService.save(job);
    }

    public JobDto updateJob(int id, JobUpdateDto updatedJob) throws ResourceNotFoundException {
        return jobsService.update(id, updatedJob);
    }

    public void removeJob(int id) throws ResourceNotFoundException {
        jobsService.remove(id);
    }
}
