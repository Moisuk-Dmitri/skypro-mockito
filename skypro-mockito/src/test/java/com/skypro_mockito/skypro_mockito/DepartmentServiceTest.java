package com.skypro_mockito.skypro_mockito;

import com.skypro_mockito.skypro_mockito.Domain.Employee;
import com.skypro_mockito.skypro_mockito.Exceptions.EmptyMapException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import com.skypro_mockito.skypro_mockito.Services.DepartmentService;
import com.skypro_mockito.skypro_mockito.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
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
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    @DisplayName("Корректный тест получения макс зарплаты")
    public void shouldFindMaxSalary() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertEquals(departmentService.findMaxSalaryByDepartment(110), 68000);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Некорректный тест получения макс зарплаты с неправильным номером отдела")
    public void shouldThrowExceptionWhenFindMaxSalary() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertThrows(EmptyMapException.class, () -> departmentService.findMaxSalaryByDepartment(123));

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения мин зарплаты")
    public void shouldFindMinSalary() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertEquals(departmentService.findMinSalaryByDepartment(110), 48000);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Некорректный тест получения мин зарплаты с неправильным номером отдела")
    public void shouldThrowExceptionWhenFindMinSalary() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertThrows(EmptyMapException.class, () -> departmentService.findMinSalaryByDepartment(123));

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения списка рабочих по номеру отдела")
    public void shouldReturnListOfEmployeesByDepartment() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        List<Employee> expectedEmployees = List.of(
                new Employee("Sophie", "Elena", "Wilson", 110, 48000),
                new Employee("Benjamin", "Scott", "Perez", 110, 68000)
        );

        assertIterableEquals(departmentService.getAllEmployeesByDepartment(110), expectedEmployees);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения пустого списка рабочих по номеру отдела")
    public void shouldReturnEmptyListOfEmployeesByDepartment() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertEquals(departmentService.getAllEmployeesByDepartment(123).size(), 0);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Некорректный тест получения списка рабочих с неправильным номером отдела")
    public void shouldThrowExceptionGetAllWhenIncorrectDepartmentNumber() {
        assertThrows(InvalidDepartmentNumberException.class, () -> departmentService.getAllEmployeesByDepartment(-2));

        verify(employeeServiceMock, never()).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения суммы зарплат по номеру отдела")
    public void shouldReturnSumOfSalariesByDepartment() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertEquals(departmentService.getSumSalaryByDepartment(110), 116000);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения суммы зарпал по несуществующему номеру отдела")
    public void shouldReturnNullSumWhenIncorrectDepartmentNumber() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        assertEquals(departmentService.getSumSalaryByDepartment(123), 0);

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Некорректный тест получения суммы из несуществующего отдела")
    public void shouldThrowExceptionSumWhenIncorrectDepartmentNumber() {
        assertThrows(InvalidDepartmentNumberException.class, () -> departmentService.getSumSalaryByDepartment(-2));

        verify(employeeServiceMock, never()).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения отсортированного списка рабочих")
    public void shouldReturnMapOfEmployeesSorted() {
        when(employeeServiceMock.getAll()).thenReturn(employees);

        Set<Integer> expectedMapSize = new HashSet<>();
        for (int i = 0; i < employees.size(); i++) {
            expectedMapSize.add(employees.get(i).getDepartmentNumber());
        }

        assertEquals(departmentService.getSortedEmployeesByDepartments().size(), expectedMapSize.size());

        verify(employeeServiceMock, times(1)).getAll();
    }

    @Test
    @DisplayName("Корректный тест получения пустого отсртированного списка рабочих")
    public void shouldReturnEmptyMapOfEmployeesSorted() {
        when(employeeServiceMock.getAll()).thenReturn(List.of());

        assertEquals(departmentService.getSortedEmployeesByDepartments().size(), 0);

        verify(employeeServiceMock, times(1)).getAll();
    }
}
