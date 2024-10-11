package com.skypro_mockito.skypro_mockito;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Exceptions.AddingEmployeeException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidSalaryNumberException;
import com.skypro_mockito.skypro_mockito.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void addingCorrectEmployeesTest() {
        Employee employee1 = new Employee("Sophie", "Elena", "Wilson", 110, 48000);
        Employee employee2 = new Employee("Daniel", "Victor", "Martinez", 205, 52000);
        Employee employee3 = new Employee("Olga", "Sergeevna", "Petrova", 310, 59000);
        Employee employee4 = new Employee("Michael", "Alexander", "Taylor", 410, 75000);
        Employee employee5 = new Employee("Lucas", "David", "Harris", 205, 62000);
        Employee employee6 = new Employee("Natalia", "", "Rogers", 310, 53000);
        Employee employee7 = new Employee("Benjamin", "Scott", "Perez", 110, 68000);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        employeeService.addEmployee(employee4);
        employeeService.addEmployee(employee5);
        employeeService.addEmployee(employee6);
        employeeService.addEmployee(employee7);

        int actual = employeeService.getAll().size();
        int expected = 7;

        assertEquals(actual, expected);
    }

    @Test
    public void addingIncorrectNameEmployeeTest() {
        Employee employee = new Employee("sophie", "Elena", "Wil123son", 110, 48000);

        assertThrows(AddingEmployeeException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void addingIncorrectDepartmentNumberEmployeeTest() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", -50, 48000);

        assertThrows(InvalidDepartmentNumberException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void addingIncorrectSalaryEmployeeTest() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", 110, -12000);

        assertThrows(InvalidSalaryNumberException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void addingSameEmployeeTwiceTest() {
        Employee employee1 = new Employee("Sophie", "Elena", "Wilson", 110, 12000);
        Employee employee2 = new Employee("Sophie", "Elena", "Wilson", 110, 12000);

        employeeService.addEmployee(employee1);
        assertThrows(AddingEmployeeException.class, () -> employeeService.addEmployee(employee2));
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", 110, 12000);
        Employee expectedEmployee = new Employee("Sophie", "Elena", "Wilson", 110, 12000);

        employeeService.addEmployee(employee);
        assertEquals(employeeService.getEmployeeById(0), expectedEmployee);
    }

    @Test
    public void getIncorrectEmployeeByIdTest() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", 110, 12000);

        employeeService.addEmployee(employee);
        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(3));
    }

    @Test
    public void deleteEmployeeById() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", 110, 12000);

        employeeService.addEmployee(employee);
        employeeService.deleteEmployeeById(0);
        assertEquals(employeeService.getAll().size(), 0);
    }

    @Test
    public void deleteIncorrectEmployeeById() {
        Employee employee = new Employee("Sophie", "Elena", "Wilson", 110, 12000);

        employeeService.addEmployee(employee);
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployeeById(3));
    }
}
