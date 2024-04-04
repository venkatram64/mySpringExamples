package com.venkat.mypage.controller;

import com.venkat.mypage.model.Employee;
import com.venkat.mypage.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAllEmp(@RequestParam(required = false, name="firstName") String firstName,
                            @RequestParam(required = false) String lastName, Model model,
                            HttpServletRequest request){



        //get session object
        HttpSession session = request.getSession();
        //storing in session
        if(firstName != null && lastName != null){
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
        }
        firstName = (String) session.getAttribute("firstName");
        lastName = (String) session.getAttribute("lastName");
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee_page";
    }

    @GetMapping("/createPage")
    public String getCreateEmpPage(Model model){
        model.addAttribute("newEmp", new Employee());
        return "create_employee_page";
    }

    @PostMapping("/create")
    public String createEmpPage(@ModelAttribute Employee employee){

        employeeService.create(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateEmpPage(@PathVariable("id") int id, Model model){

        Employee employee = employeeService.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid employee id: " + id));
        model.addAttribute("employee", employee);
        return "edit_employee_page";
    }

    @PostMapping("/edit/{id}")
    public String updateEmpPage(@PathVariable("id") int id, @ModelAttribute Employee employee){
        Employee emp = employeeService.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid employee id: " + id));
        employee.setId(id);
        employee.setEmpNo(emp.getEmpNo());
        employeeService.update(employee);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") int id){
        employeeService.deleteById(id);
        return "redirect:/employee";
    }
}
