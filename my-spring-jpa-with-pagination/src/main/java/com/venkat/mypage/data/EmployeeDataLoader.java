package com.venkat.mypage.data;

import com.github.javafaker.Faker;
import com.venkat.mypage.model.Employee;
import com.venkat.mypage.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class EmployeeDataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(EmployeeDataLoader.class);

    private final Faker faker;
    private final EmployeeRepository employeeRepository;

    public EmployeeDataLoader(Faker faker, EmployeeRepository employeeRepository) {
        this.faker = faker;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Employee Data...");
        List<Employee> employees = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Employee(null,faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        "VV-" + faker.number().digits(5)
                        )
                ).toList();
        employeeRepository.saveAll(employees);
    }
}
