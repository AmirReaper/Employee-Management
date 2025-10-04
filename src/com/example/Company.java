package com.example;

import com.example.exception.EmployeeNotFoundException;
import com.example.exception.ExportException;
import com.example.model.Employee;
import com.example.service.EmployeeReportExporter;
import java.util.ArrayList;

public class Company {
    private String companyName;
    private ArrayList<Employee> employees;

    public Company(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Company name cannot be empty.");
        }
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("❌ Employee cannot be null.");
        }
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
        if (index < 0 || index >= employees.size()) {
            throw new EmployeeNotFoundException("Employee not found at index: " + index);
        }
        return employees.get(index);
    }

    public Employee findHighestPaidEmployee() {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees available for comparison.");
        }
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

    /**
     * New method for v2.1.0 - Export company report
     */
    public void exportCompanyReport() {
        try {
            EmployeeReportExporter exporter = new EmployeeReportExporter("company_report.txt");
            String report = generateCompanyReport();
            exporter.exportToFile(report);
            System.out.println("✅ Company report exported to company_report.txt");
        } catch (ExportException e) {
            System.out.println("❌ Export failed: " + e.getMessage());
        }
    }

    private String generateCompanyReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Company: ").append(companyName).append("\n");
        sb.append("Total Employees: ").append(employees.size()).append("\n\n");

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            sb.append(i + 1).append(". ").append(emp.toString()).append("\n");
        }

        if (!employees.isEmpty()) {
            Employee highestPaid = findHighestPaidEmployee();
            sb.append("\nHighest Paid Employee: ").append(highestPaid.getName())
                    .append(" - $").append(highestPaid.calculateWage());
        }

        return sb.toString();
    }
}