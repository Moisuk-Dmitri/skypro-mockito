package com.skypro_mockito.skypro_mockito;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import com.skypro_mockito.skypro_mockito.Services.DepartmentService;
import com.skypro_mockito.skypro_mockito.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private DepartmentService departmentService;

    List<Employee> employees = List.of(
            new Employee("Sophie", "Elena", "Wilson", 110, 48000),
            new Employee("Daniel", "Victor", "Martinez", 205, 52000),
            new Employee("Olga", "Sergeevna", "Petrova", 310, 59000),
            new Employee("Michael", "Alexander", "Taylor", 410, 75000),
            new Employee("Lucas", "David", "Harris", 205, 62000),
            new Employee("Natalia", "", "Rogers", 310, 53000),
            new Employee("Benjamin", "Scott", "Perez", 110, 68000)
    );

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentService(employeeService);
    }

    @Test
    public void maxCorrectSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertEquals(departmentService.findMaxSalaryByDepartment(110), 68000);
    }

    @Test
    public void maxIncorrectSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertThrows(RuntimeException.class, () -> departmentService.findMaxSalaryByDepartment(123));
    }

    @Test
    public void minCorrectSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertEquals(departmentService.findMinSalaryByDepartment(110), 48000);
    }

    @Test
    public void minIncorrectSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertThrows(RuntimeException.class, () -> departmentService.findMinSalaryByDepartment(123));
    }

    @Test
    public void getAllEmployeesByDepartmentCorrectTest() {
        when(employeeService.getAll()).thenReturn(employees);

        List<Employee> expectedEmployees = List.of(
                new Employee("Sophie", "Elena", "Wilson", 110, 48000),
                new Employee("Benjamin", "Scott", "Perez", 110, 68000)
        );

        assertIterableEquals(departmentService.getAllEmployeesByDepartment(110), expectedEmployees);
    }

    @Test
    public void getAllEmployeesByDepartmentEmptyTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertEquals(departmentService.getAllEmployeesByDepartment(123).size(), 0);
    }

    @Test
    public void getAllEmployeesByDepartmentIncorrectTest() {
        assertThrows(RuntimeException.class, () -> departmentService.getAllEmployeesByDepartment(-2));
    }

    @Test
    public void sumCorrectSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertEquals(departmentService.getSumSalaryByDepartment(110), 116000);
    }

    @Test
    public void sumNullSalaryTest() {
        when(employeeService.getAll()).thenReturn(employees);

        assertEquals(departmentService.getSumSalaryByDepartment(123), 0);
    }

    @Test
    public void sumIncorrectSalaryTest() {
        assertThrows(RuntimeException.class, () -> departmentService.getSumSalaryByDepartment(-2));
    }

    @Test
    public void getSortedEmployeesByDepartmentsCorrectTest() {
        when(employeeService.getAll()).thenReturn(employees);

        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>();
        expectedEmployees.put(110, List.of(new Employee("Sophie", "Elena", "Wilson", 110, 48000),
                new Employee("Benjamin", "Scott", "Perez", 110, 68000)));
        expectedEmployees.put(205, List.of(new Employee("Daniel", "Victor", "Martinez", 205, 52000),
                new Employee("Lucas", "David", "Harris", 205, 62000)));
        expectedEmployees.put(310, List.of(new Employee("Olga", "Sergeevna", "Petrova", 310, 59000),
                new Employee("Natalia", "", "Rogers", 310, 53000)));
        expectedEmployees.put(410, List.of(new Employee("Michael", "Alexander", "Taylor", 410, 75000)));

        assertEquals(departmentService.getSortedEmployeesByDepartments(), expectedEmployees);
    }

    @Test
    public void getSortedEmployeesByDepartmentsEmptyTest() {
        assertEquals(departmentService.getSortedEmployeesByDepartments().size(), 0);
    }
}
