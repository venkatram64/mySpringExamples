package com.venkat.mypage.service;

import com.github.javafaker.Faker;
import com.venkat.mypage.model.Employee;
import com.venkat.mypage.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    public EmployeeService(EmployeeRepository employeeRepository, Faker faker) {
        this.employeeRepository = employeeRepository;
        this.faker = faker;
    }

    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> findById(int id){
        return this.employeeRepository.findById(id);
    }

    public Page<Employee> findPaginated(int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.employeeRepository.findAll(pageable);
    }

    public Employee create(Employee employee){
        //random generation employee number from faker
        employee.setEmpNo("VV-" + faker.number().digits(5));
        return this.employeeRepository.save(employee);
    }

    public Employee update(Employee employee){
        return this.employeeRepository.save(employee);
    }

    public void deleteById(int id){
        this.employeeRepository.deleteById(id);
    }
}
