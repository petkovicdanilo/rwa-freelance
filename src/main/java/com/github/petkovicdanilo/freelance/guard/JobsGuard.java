package com.github.petkovicdanilo.freelance.guard;

import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobsGuard {

    private final JobsRepository jobsRepository;

    private final UsersRepository usersRepository;

    public boolean checkJobId(Authentication authentication, int id, boolean allowAdmin) {
        String email = authentication.getName();

        UserEntity currentUser = usersRepository.findByEmail(email).get();

        Optional<JobEntity> opt = jobsRepository.findById(id);

        if(!opt.isPresent()) {
            return false;
        }

        if(allowAdmin && currentUser.isAdmin()) {
            return true;
        }

        JobEntity jobEntity = opt.get();
        UserEntity employer = jobEntity.getEmployer();

        return employer.getEmail().compareTo(email) == 0;
    }
}
