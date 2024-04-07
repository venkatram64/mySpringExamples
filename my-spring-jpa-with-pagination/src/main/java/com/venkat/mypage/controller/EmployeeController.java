package com.venkat.mypage.controller;

import com.venkat.mypage.model.Employee;
import com.venkat.mypage.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
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
    public String getAllEmp(Model model){
        return getPaginatedEmployees(1, "firstName", "asc", model);
    }

    // /page/1?sortField=name&sortDir=asc
    @GetMapping("/page/{pageNo}")
    public String getPaginatedEmployees(@PathVariable(value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model){
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> employees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", employees);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);//sort direction --> ascending or decending
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
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
