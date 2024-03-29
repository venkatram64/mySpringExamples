package com.venkat.myspringjpa.service;

import com.venkat.myspringjpa.model.Customer;
import com.venkat.myspringjpa.repository.CustomerRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    //constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }

    public List<Customer> findAll(int pageNo, int pageSize, String sortBy){//pagination
        Sort sort = Sort.by(sortBy);
        PageRequest pr = PageRequest.of(pageNo, pageSize, sort);
        Page<Customer> pages = this.customerRepository.findAll(pr);
        List<Customer> customers = pages.getContent();
        return customers;
    }

    public Customer create(Customer customer){
        return this.customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return this.customerRepository.save(customer);
    }

    public void deleteById(int id){
        this.customerRepository.deleteById(id);
    }
}
