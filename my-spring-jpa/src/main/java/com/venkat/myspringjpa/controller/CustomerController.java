package com.venkat.myspringjpa.controller;

import com.venkat.myspringjpa.model.Customer;
import com.venkat.myspringjpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //pagination
    @GetMapping("/page")
    public List<Customer> findAll(@RequestParam(value="pageNo", defaultValue = "0") int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        return customerService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PutMapping("/")
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.update(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        customerService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

}
