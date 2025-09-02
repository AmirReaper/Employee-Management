package com.example;

import java.util.ArrayList;

public class Company {
    private String companyName;
    private ArrayList<Employee> employees;

    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

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

    public Employee getEmployee(int index) {
        if (index >= 0 && index < employees.size())
            return employees.get(index);
        return null;
    }

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

    public int getEmployeeCount() {
        return employees.size();
    }
}
