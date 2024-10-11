package com.skypro_mockito.skypro_mockito.Controllers;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/max")
    public Integer getMaxSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.findMaxSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Integer getMinSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.findMinSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public Integer getSumSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getSumSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable("id") int id) {
        return departmentService.getAllEmployeesByDepartment(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getSortedEmployeesByDepartments() {
        return departmentService.getSortedEmployeesByDepartments();
    }
}
