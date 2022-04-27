package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.BadRequestException;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationSaveDto;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationDto;
import com.github.petkovicdanilo.freelance.model.common.ApplicationStatus;
import com.github.petkovicdanilo.freelance.model.entity.ApplicationEntity;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.model.mapper.ApplicationsMapper;
import com.github.petkovicdanilo.freelance.repository.ApplicationsRepository;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final UsersRepository usersRepository;
    private final JobsRepository jobsRepository;
    private final ApplicationsRepository applicationsRepository;

    private final ApplicationsMapper applicationsMapper;

    public List<ApplicationDto> getUsersApplications(int userId) throws ResourceNotFoundException {
        UserEntity user = getUser(userId);

        return user.getApplications()
                .stream()
                .map(applicationsMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ApplicationDto> getJobApplications(int jobId) throws ResourceNotFoundException {
        JobEntity job = getJob(jobId);

        return job.getApplications()
                .stream()
                .map(applicationsMapper::toDto)
                .collect(Collectors.toList());
    }

    public ApplicationDto getJobApplicationForUser(int jobId, int userId) throws ResourceNotFoundException {
        ApplicationEntity applicationEntity = applicationsRepository.findByJobIdAndCandidateId(jobId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.APPLICATION));

        return applicationsMapper.toDto(applicationEntity);
    }

    public ApplicationDto save(int jobId, int userId, ApplicationSaveDto application) throws ResourceNotFoundException, BadRequestException {
        JobEntity job = getJob(jobId);
        UserEntity candidate = getUser(userId);

        if(job.getEmployer().getId() == userId) {
            throw new BadRequestException("Cannot apply for your own job");
        }

        if(applicationsRepository.findByJobIdAndCandidateId(jobId, userId).isPresent()) {
            throw new BadRequestException("User with id " + userId + " has already applied for job with id " + jobId);
        }

        ApplicationEntity applicationEntity = applicationsMapper.toEntity(application);
        applicationEntity.setJob(job);
        applicationEntity.setCandidate(candidate);

        applicationEntity = applicationsRepository.save(applicationEntity);

        return applicationsMapper.toDto(applicationEntity);
    }

    public ApplicationDto update(int jobId, int userId, ApplicationSaveDto application) throws ResourceNotFoundException {
        JobEntity jobEntity = getJob(jobId);
        UserEntity userEntity = getUser(userId);

        ApplicationEntity applicationEntity = applicationsRepository.findByJobIdAndCandidateId(jobId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.APPLICATION));
        applicationEntity.setText(application.getText());

        applicationEntity = applicationsRepository.save(applicationEntity);

        return applicationsMapper.toDto(applicationEntity);
    }

    public void remove(int jobId, int userId) throws ResourceNotFoundException {
        ensureJobExists(jobId);
        ensureUserExists(userId);

        ApplicationEntity applicationEntity = applicationsRepository.findByJobIdAndCandidateId(jobId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.APPLICATION));

        applicationsRepository.delete(applicationEntity);
    }

    public void acceptApplication(int jobId, int userId) throws ResourceNotFoundException, BadRequestException {
        JobEntity jobEntity = getJob(jobId);
        ensureUserExists(userId);

        List<ApplicationEntity> jobApplications = jobEntity.getApplications();

        if(!applicationsRepository.existsByJobIdAndCandidateId(jobId, userId)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.APPLICATION);
        }

        if(!jobEntity.isActive()) {
            throw new BadRequestException("Job is not active");
        }

        jobApplications.forEach(applicationEntity -> {
            if(applicationEntity.getCandidate().getId() == userId) {
                applicationEntity.setStatus(ApplicationStatus.ACCEPTED);
            }
            else {
                applicationEntity.setStatus(ApplicationStatus.REJECTED);
            }
        });
        jobEntity.setActive(false);

        jobsRepository.save(jobEntity);
    }

    private JobEntity getJob(int jobId) throws ResourceNotFoundException {
        return jobsRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.JOB));
    }

    private void ensureJobExists(int jobId) throws ResourceNotFoundException {
        if(!jobsRepository.existsById(jobId)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.JOB);
        }
    }

    private UserEntity getUser(int userId) throws ResourceNotFoundException {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));
    }

    private void ensureUserExists(int userId) throws ResourceNotFoundException {
        if(!usersRepository.existsById(userId)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.USER);
        }
    }

}