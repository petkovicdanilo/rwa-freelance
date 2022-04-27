package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.ApplicationsRestController;
import com.github.petkovicdanilo.freelance.exception.BadRequestException;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationDto;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationSaveDto;
import com.github.petkovicdanilo.freelance.service.ApplicationsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Applications")
@RequiredArgsConstructor
public class ApplicationsRestControllerImpl implements ApplicationsRestController {
    private final ApplicationsService applicationsService;

    @Override
    public List<ApplicationDto> getUsersApplications(int userId) throws ResourceNotFoundException {
        return applicationsService.getUsersApplications(userId);
    }

    @Override
    public List<ApplicationDto> getApplicationsForJob(int jobId) throws ResourceNotFoundException {
        return applicationsService.getJobApplications(jobId);
    }

    @Override
    public ApplicationDto getUsersApplicationForJob(int jobId, int userId) throws ResourceNotFoundException {
        return applicationsService.getJobApplicationForUser(jobId, userId);
    }

    @Override
    public ApplicationDto saveApplication(int jobId, int userId, ApplicationSaveDto application) throws BadRequestException, ResourceNotFoundException {
        return applicationsService.save(jobId, userId, application);
    }

    @Override
    public ApplicationDto updateApplication(int jobId, int userId, ApplicationSaveDto application) throws ResourceNotFoundException {
        return applicationsService.update(jobId, userId, application);
    }

    @Override
    public void removeApplication(int jobId, int userId) throws ResourceNotFoundException {
        applicationsService.remove(jobId, userId);
    }

    @Override
    public void acceptApplication(int jobId, int userId) throws BadRequestException, ResourceNotFoundException {
        applicationsService.acceptApplication(jobId, userId);
    }
}
