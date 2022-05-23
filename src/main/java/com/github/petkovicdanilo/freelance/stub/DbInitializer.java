package com.github.petkovicdanilo.freelance.stub;

import com.github.petkovicdanilo.freelance.model.common.ApplicationStatus;
import com.github.petkovicdanilo.freelance.model.common.Gender;
import com.github.petkovicdanilo.freelance.model.entity.ApplicationEntity;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.repository.ApplicationsRepository;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DbInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final JobsRepository jobsRepository;
    private final TechnologiesRepository technologiesRepository;

    private final ApplicationsRepository applicationsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        for(String arg: args) {
            log.info(arg);
        }

        if(!Arrays.asList(args).contains("--init-db")) {
            return;
        }

        log.info("Initializing database");

        TechnologyEntity java = TechnologyEntity.builder().name("Java").build();
        TechnologyEntity cSharp = TechnologyEntity.builder().name("C#").build();
        TechnologyEntity spring = TechnologyEntity.builder().name("Spring").build();
        TechnologyEntity cpp = TechnologyEntity.builder().name("C++").build();

        technologiesRepository.saveAll(Arrays.asList(java, cSharp, spring, cpp));
        log.info("Initialized technologies");

        UserEntity admin = UserEntity.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@example.com")
                .password(passwordEncoder.encode("admin"))
                .isAdmin(true)
                .build();
        UserEntity user1 = UserEntity.builder()
                .firstName("Pera")
                .lastName("Peric")
                .email("pera.peric@example.com")
                .password(passwordEncoder.encode("pera"))
                .gender(Gender.MALE)
                .technology(java)
                .technology(spring)
                .build();
        UserEntity user2 = UserEntity.builder()
                .firstName("Milica")
                .lastName("Milicevic")
                .email("milica@example.com")
                .password(passwordEncoder.encode("milica"))
                .gender(Gender.FEMALE)
                .technology(cSharp)
                .technology(java)
                .build();
        UserEntity user3 = UserEntity.builder()
                .firstName("Vanja")
                .lastName("Vanjic")
                .email("vanja@example.com")
                .password(passwordEncoder.encode("vanja"))
                .technology(cpp)
                .build();

        usersRepository.saveAll(Arrays.asList(admin, user1, user2, user3));
        log.info("Initialized users");

        JobEntity job1 = JobEntity.builder()
                .employer(user1)
                .title("job 1")
                .description("description for job 1")
                .price(100)
                .technology(java)
                .technology(spring)
                .active(false)
                .build();
        JobEntity job2 = JobEntity.builder()
                .employer(user1)
                .title("job 2")
                .description("description for job 2")
                .price(200)
                .technology(java)
                .technology(spring)
                .build();
        JobEntity job3 = JobEntity.builder()
                .employer(user2)
                .title("job 3")
                .description("description for job 3")
                .price(300)
                .technology(cSharp)
                .technology(cpp)
                .build();

        jobsRepository.saveAll(Arrays.asList(job1, job2, job3));
        log.info("Initialized jobs");

        ApplicationEntity application1 = ApplicationEntity.builder()
                .candidate(user2)
                .job(job1)
                .text("User2 applying for job1")
                .status(ApplicationStatus.ACCEPTED)
                .build();
        ApplicationEntity application2 = ApplicationEntity.builder()
                .candidate(user3)
                .job(job1)
                .text("User3 applying for job1")
                .status(ApplicationStatus.REJECTED)
                .build();
        ApplicationEntity application3 = ApplicationEntity.builder()
                .candidate(user2)
                .job(job2)
                .text("User2 applying for job2")
                .status(ApplicationStatus.PENDING)
                .build();
        ApplicationEntity application4 = ApplicationEntity.builder()
                .candidate(user3)
                .job(job2)
                .text("User3 applying for job2")
                .status(ApplicationStatus.PENDING)
                .build();
        ApplicationEntity application5 = ApplicationEntity.builder()
                .candidate(user3)
                .job(job3)
                .text("User3 applying for job3")
                .status(ApplicationStatus.PENDING)
                .build();
        ApplicationEntity application6 = ApplicationEntity.builder()
                .candidate(user1)
                .job(job3)
                .text("User1 applying for job3")
                .status(ApplicationStatus.PENDING)
                .build();

        applicationsRepository.saveAll(
                Arrays.asList(application1, application2, application3, application4, application5, application6));
        log.info("Initialized applications");

    }
}
