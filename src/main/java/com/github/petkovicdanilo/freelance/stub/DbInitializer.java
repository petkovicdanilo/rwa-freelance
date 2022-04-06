package com.github.petkovicdanilo.freelance.stub;

import com.github.petkovicdanilo.freelance.model.Job;
import com.github.petkovicdanilo.freelance.model.Technology;
import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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

    @Override
    public void run(String... args) throws Exception {
        if(!Arrays.asList(args).contains("--init-db")) {
            return;
        }

        log.info("Initializing database");

        User user1 = User.builder()
                .firstName("Pera")
                .lastName("Peric")
                .email("pera.peric@example.com")
                .password("pera")
                .gender(User.Gender.MALE)
                .build();
        User user2 = User.builder()
                .firstName("Milica")
                .lastName("Milicevic")
                .email("milica@example.com")
                .password("milica")
                .gender(User.Gender.FEMALE)
                .build();
        User user3 = User.builder()
                .firstName("Vanja")
                .lastName("Vanjic")
                .email("vanja@example.com")
                .password("vanja")
                .build();

        usersRepository.saveAll(Arrays.asList(user1, user2, user3));
        log.info("Initialized users");

        Job job1 = Job.builder()
                .description("job 1")
                .price(100)
                .build();
        Job job2 = Job.builder()
                .description("job 2")
                .price(200)
                .build();
        Job job3 = Job.builder()
                .description("job 3")
                .price(300)
                .build();

        jobsRepository.saveAll(Arrays.asList(job1, job2, job3));
        log.info("Initialized jobs");

        Technology java = Technology.builder().name("Java").build();
        Technology cSharp = Technology.builder().name("C#").build();
        Technology spring = Technology.builder().name("Spring").build();
        Technology cpp = Technology.builder().name("C++").build();

        technologiesRepository.saveAll(Arrays.asList(java, cSharp, spring, cpp));
        log.info("Initialized technologies");
    }
}
