package com.skypro_mockito.skypro_mockito.Services;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Exceptions.AddingEmployeeException;
import com.skypro_mockito.skypro_mockito.Exceptions.EmptyMapException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidSalaryNumberException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (!(StringUtils.isAlpha(employee.getFirstName()) && StringUtils.isAllLowerCase(StringUtils.substring(employee.getFirstName(), 1)))
                || !((StringUtils.isAlpha(employee.getMiddleName()) && StringUtils.isAllLowerCase(StringUtils.substring(employee.getMiddleName(), 1))) || StringUtils.isEmpty(employee.getMiddleName()))
                || !(StringUtils.isAlpha(employee.getLastName()) && StringUtils.isAllLowerCase(StringUtils.substring(employee.getLastName(), 1)))) {
            throw new AddingEmployeeException("Invalid employee name");
        }

        if (employeeList.stream()
                .anyMatch(e -> e.equals(employee))) {
            throw new AddingEmployeeException("Such employee already exists");
        }

        if (employee.getSalary() < 0) {
            throw new InvalidSalaryNumberException();
        }

        if(employee.getDepartmentNumber() < 0) {
            throw new InvalidDepartmentNumberException();
        }

        employee.setFirstName(StringUtils.capitalize(employee.getFirstName()));
        employee.setMiddleName(StringUtils.capitalize(employee.getMiddleName()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName()));

        employeeList.add(employee);
    }


    public Employee getEmployeeById(int id) {
        return employeeList.get(id);
    }

    public void deleteEmployeeById(int id) {
        employeeList.remove(id);
    }

    public List<Employee> getAll() {
        return employeeList.stream()
                .collect(Collectors.toList());
    }
}
