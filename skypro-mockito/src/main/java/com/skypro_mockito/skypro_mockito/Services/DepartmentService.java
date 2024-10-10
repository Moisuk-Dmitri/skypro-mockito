package com.skypro_mockito.skypro_mockito.Services;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Exceptions.EmptyMapException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Integer findMinSalaryByDepartment(int departmentNumber) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentNumber() == departmentNumber)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmptyMapException("No such salary"))
                .getSalary();
    }

    public Integer findMaxSalaryByDepartment(int departmentNumber) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentNumber() == departmentNumber)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmptyMapException("No such salary"))
                .getSalary();
    }

    public List<Employee> getAllEmployeesByDepartment(int departmentNumber) {
        if (departmentNumber < 0) {
            throw new InvalidDepartmentNumberException();
        }

        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentNumber() == departmentNumber)
                .collect(Collectors.toList());
    }

    public Integer getSumSalaryByDepartment(int departmentNumber) {
        if (departmentNumber < 0) {
            throw new InvalidDepartmentNumberException();
        }

        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentNumber() == departmentNumber)
                .mapToInt(Employee::getSalary).sum();
    }

    public Map<Integer, List<Employee>> getSortedEmployeesByDepartments() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentNumber));
    }
}
