package com.venkat.post.data;


import com.github.javafaker.Faker;
import com.venkat.post.Repository.UserRepository;
import com.venkat.post.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class LoadData implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(LoadData.class);

    private final Faker faker;
    private final UserRepository userRepository;

    public LoadData(Faker faker, UserRepository userRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading User Data...");
        List<User> users = IntStream.rangeClosed(1, 100)
                .mapToObj(n -> new User(null,faker.name().firstName(),faker.name().lastName(),
                        faker.internet().emailAddress(),"password","Male", faker.address().fullAddress(), true,"Developer" )
                ).toList();
        this.userRepository.saveAll(users);
    }
}
