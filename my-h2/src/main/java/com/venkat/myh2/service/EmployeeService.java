package com.venkat.myh2.service;

import com.venkat.myh2.model.Employee;
import com.venkat.myh2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public boolean create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean update(Employee employee) {
        return employeeRepository.update(employee);
    }

    public boolean deleteById(int id) {
        return employeeRepository.deleteById(id);
    }
}
