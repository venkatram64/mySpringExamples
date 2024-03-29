package com.venkat.myh2.controller;

import com.venkat.myh2.model.Employee;
import com.venkat.myh2.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    //constructor injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody Employee employee){
        boolean flag = employeeService.create(employee);
        String result = flag == true ? HttpStatus.CREATED.name() : "Not Created";
        return ResponseEntity.ok(result);
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody Employee employee){
        boolean flag = employeeService.update(employee);
        String result = flag == true ? "Updated" : "Not Update";
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        boolean flag = employeeService.deleteById(id);
        String result = flag == true ? "Deleted" : "Not Deleted";
        return ResponseEntity.ok(result);
    }
}
