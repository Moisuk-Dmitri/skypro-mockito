package com.skypro_mockito.skypro_mockito.Controllers;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public void addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("middleName") String middleName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") int department,
                              @RequestParam("salary") int salary) {
        employeeService.addEmployee(new Employee(firstName, middleName, lastName, department, salary));
    }

    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
