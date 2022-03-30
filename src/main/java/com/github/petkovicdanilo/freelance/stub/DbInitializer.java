package com.github.petkovicdanilo.freelance.stub;

import com.github.petkovicdanilo.freelance.model.Job;
import com.github.petkovicdanilo.freelance.model.Technology;
import com.github.petkovicdanilo.freelance.model.User;
import com.github.petkovicdanilo.freelance.repository.JobsRepository;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final JobsRepository jobsRepository;
    private final TechnologiesRepository technologiesRepository;

    private final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Override
    public void run(String... args) throws Exception {
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
        logger.info("Initialized users");

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
        logger.info("Initialized jobs");

        Technology java = Technology.builder().name("Java").build();
        Technology cSharp = Technology.builder().name("C#").build();
        Technology spring = Technology.builder().name("Spring").build();
        Technology cpp = Technology.builder().name("C++").build();

        technologiesRepository.saveAll(Arrays.asList(java, cSharp, spring, cpp));
        logger.info("Initialized technologies");
    }
}