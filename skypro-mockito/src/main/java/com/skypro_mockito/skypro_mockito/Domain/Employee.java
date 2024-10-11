package com.skypro_mockito.skypro_mockito.Domain;

import com.skypro_mockito.skypro_mockito.Exceptions.InvalidDepartmentNumberException;
import com.skypro_mockito.skypro_mockito.Exceptions.InvalidSalaryNumberException;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private int departmentNumber;
    private int salary;

    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, int departmentNumber, int salary) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.departmentNumber = departmentNumber;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        if (departmentNumber < 0) {
            throw new InvalidDepartmentNumberException("Invalid department number");
        }

        this.departmentNumber = departmentNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            throw new InvalidSalaryNumberException("Invalid salary");
        }

        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, departmentNumber, salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentNumber == employee.departmentNumber &&
                salary == employee.salary &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(middleName, employee.middleName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentNumber=" + departmentNumber +
                ", salary=" + salary +
                '}';
    }
}