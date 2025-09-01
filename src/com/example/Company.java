package com.example;

import java.util.ArrayList;

/**
 * Represents a Company which manages multiple employees.
 */
public class Company {
    private String companyName;
    private ArrayList<Employee> employees;

    /**
     * Constructor to initialize company with a name.
     */
    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    /**
     * Adds a new employee to the company.
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Prints all employees in the company.
     */
    public void printAllEmployees() {
        System.out.println("\n--- " + companyName + " Employee List ---");
        if (employees.isEmpty()) {
            System.out.println("No employees in the company.");
            return;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i));
        }
    }

    /**
     * Retrieves an employee by index.
     */
    public Employee getEmployee(int index) {
        if (index >= 0 && index < employees.size())
            return employees.get(index);
        return null;
    }

    /**
     * Finds the employee with the highest base salary.
     */
    public Employee findHighestPaidEmployee() {
        if (employees.isEmpty()) return null;
        Employee highest = employees.get(0);
        for (Employee emp : employees) {
            if (emp.calculateWage() > highest.calculateWage()) {
                highest = emp;
            }
        }
        return highest;
    }

    /**
     * Gets the number of employees in the company.
     */
    public int getEmployeeCount() {
        return employees.size();
    }
}
