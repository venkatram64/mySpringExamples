package com.venkat.myspringjpa.data;

import com.github.javafaker.Faker;
import com.venkat.myspringjpa.model.Address;
import com.venkat.myspringjpa.model.Customer;
import com.venkat.myspringjpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);

    private final Faker faker;
    private final CustomerRepository customerRepository;

    //constructor injection
    public SampleDataLoader(Faker faker, CustomerRepository customerRepository) {
        this.faker = faker;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       logger.info("Loading Sample Data...");
        List<Customer> customers = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Customer(null,faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(),
                        new Address(null, faker.address().secondaryAddress(),
                                faker.address().city(), faker.address().state(),
                                faker.address().zipCode())
                        )).toList();
        customerRepository.saveAll(customers);
    }
}
