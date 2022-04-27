package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.exception.BadRequestException;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationDto;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface ApplicationsRestController {

    @GetMapping("/users/{userId}/applications")
    @Operation(description = "Get all job applications for given user", summary = "Get all job applications for given user")
    List<ApplicationDto> getUsersApplications(@PathVariable int userId) throws ResourceNotFoundException;

    @GetMapping("/jobs/{jobId}/applications")
    @Operation(description = "Get all applications for given job", summary = "Get all applications for given job")
    List<ApplicationDto> getApplicationsForJob(@PathVariable int jobId) throws ResourceNotFoundException;

    @GetMapping("/jobs/{jobId}/applications/{userId}")
    @Operation(description = "Get user's application for job", summary = "Get user's application for job")
    ApplicationDto getUsersApplicationForJob(@PathVariable int jobId, @PathVariable int userId) throws ResourceNotFoundException;

    @PostMapping("/jobs/{jobId}/applications/{userId}")
    @Operation(description = "Create application for job as given user", summary = "Create application for job as given user")
    ApplicationDto saveApplication(@PathVariable int jobId, @PathVariable int userId, @RequestBody ApplicationSaveDto application) throws BadRequestException, ResourceNotFoundException;

    @PutMapping("/jobs/{jobId}/applications/{userId}")
    @Operation(description = "Update user's job application", summary = "Update user's job application")
    ApplicationDto updateApplication(@PathVariable int jobId, @PathVariable int userId, @RequestBody ApplicationSaveDto application) throws ResourceNotFoundException;

    @DeleteMapping("/jobs/{jobId}/applications/{userId}")
    @Operation(description = "Delete user's job application", summary = "Delete user's job application")
    void removeApplication(@PathVariable int jobId, @PathVariable int userId) throws ResourceNotFoundException;

    @PostMapping("/jobs/{jobId}/applications/{userId}/accept")
    @Operation(description = "Accept user's job application", summary = "Accept user's job application")
    void acceptApplication(@PathVariable int jobId, @PathVariable int userId) throws BadRequestException, ResourceNotFoundException;



}
