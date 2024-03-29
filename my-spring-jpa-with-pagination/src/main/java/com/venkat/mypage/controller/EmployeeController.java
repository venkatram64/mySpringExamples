package com.venkat.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public String getAllEmp(@RequestParam(required = false, name="firstName") String firstName,
                            @RequestParam(required = false) String lastName, Model model){

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "employee_page";
    }
}
