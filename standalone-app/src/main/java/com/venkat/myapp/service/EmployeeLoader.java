package com.venkat.myapp.service;

import com.github.javafaker.Faker;
import com.venkat.myapp.model.Employee;
import com.venkat.myapp.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeLoader {

    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    @PostConstruct
    @Scheduled(fixedDelay = 60 * 60 * 1000) //every 1 hour it runs and waits for execution method time
    public void loadEmployeeData(){
        //first delete data, then load the data
        log.info("Deleting all the data from scheduler service...");
        employeeRepository.deleteAll();

        log.info("Loading Employee Data from scheduler...");
        List<Employee> employees = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Employee(null,faker.name().firstName(),
                                faker.name().lastName(),
                                faker.internet().emailAddress(),
                                "VV-" + faker.number().digits(5)
                        )
                ).toList();
        employeeRepository.saveAll(employees);
    }
}
