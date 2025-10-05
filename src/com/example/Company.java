package com.example;

import com.example.repository.EmployeeRepository;
import com.example.util.CollectionUtils;
import com.example.util.EmployeeComparators;
import com.example.exception.*;
import com.example.service.EmployeeReportExporter;
import com.example.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Company {
    private final String companyName;
    private final EmployeeRepository employeeRepository; // NEW: Using generic repository

    public Company(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Company name cannot be empty.");
        }
        this.companyName = companyName;
        this.employeeRepository = new EmployeeRepository(); // NEW
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("❌ Employee cannot be null.");
        }
        employeeRepository.add(employee); // NEW: Using repository
    }

    public void printAllEmployees() {
        System.out.println("\n--- " + companyName + " Employee List ---");
        if (employeeRepository.isEmpty()) {
            System.out.println("No employees in the company.");
            return;
        }

        int index = 1;
        for (Employee emp : employeeRepository) { // NEW: Using Iterable
            System.out.println(index++ + ". " + emp);
        }
    }

    public Employee getEmployee(int index) {
        if (index < 0 || index >= employeeRepository.size()) {
            throw new EmployeeNotFoundException("Employee not found at index: " + index);
        }
        return employeeRepository.findAll().get(index); // NEW: Using repository
    }

    public Employee findHighestPaidEmployee() {
        if (employeeRepository.isEmpty()) {
            throw new EmployeeNotFoundException("No employees available for comparison.");
        }

        // NEW: Using generic utility method
        return CollectionUtils.findMaxEmployee(
                employeeRepository.findAll(),
                EmployeeComparators.byTotalWage()
        );
    }

    public int getEmployeeCount() {
        return employeeRepository.size(); // NEW: Using repository
    }

    /**
     * NEW in v2.2.0 - Generic repository operations
     */
    public void demonstrateGenericFeatures() {
        System.out.println("\n🎯 Generic Features Demo");
        System.out.println("=" .repeat(40));

        // 1. Using generic repository methods
        System.out.println("1. Repository Operations:");
        System.out.println("   Total employees: " + employeeRepository.size());
        System.out.println("   Is empty: " + employeeRepository.isEmpty());

        // 2. Generic method with bounded type parameter
        System.out.println("\n2. Employee Type Filtering:");
        List<FullTimeEmployee> fullTimeEmployees = employeeRepository.findByType(FullTimeEmployee.class);
        List<InternEmployee> internEmployees = employeeRepository.findByType(InternEmployee.class);

        System.out.println("   Full-time employees: " + fullTimeEmployees.size());
        System.out.println("   Intern employees: " + internEmployees.size());

        // 3. Using generic utility methods
        System.out.println("\n3. Utility Operations:");
        if (!employeeRepository.isEmpty()) {
            double totalCost = CollectionUtils.calculateTotalMonthlyCost(employeeRepository.findAll());
            System.out.println("   Total monthly cost: $" + totalCost);
        }

        // 4. Comparator usage
        System.out.println("\n4. Sorting Demonstrations:");
        if (employeeRepository.size() > 1) {
            List<Employee> sortedByName = employeeRepository.findAll().stream()
                    .sorted(EmployeeComparators.byName())
                    .toList();

            List<Employee> sortedBySalary = employeeRepository.findAll().stream()
                    .sorted(EmployeeComparators.bySalary())
                    .toList();

            System.out.println("   Sorted by name (first 2):");
            sortedByName.stream().limit(2)
                    .forEach(emp -> System.out.println("     - " + emp.getName()));

            System.out.println("   Sorted by salary (first 2):");
            sortedBySalary.stream().limit(2)
                    .forEach(emp -> System.out.println("     - " + emp.getName() + ": $" + emp.getBaseSalary()));
        }

        // 5. Wildcard demonstration
        System.out.println("\n5. Wildcard Operations:");
        double totalWages = employeeRepository.calculateTotalWages(employeeRepository.findAll());
        System.out.println("   Total wages (using wildcard): $" + totalWages);
    }

    /**
     * NEW in v2.2.0 - Advanced employee search with generics
     */
    public <T extends Employee> List<T> findEmployeesByType(Class<T> type) {
        return employeeRepository.findByType(type);
    }

    /**
     * NEW in v2.2.0 - Get employees sorted by various criteria
     */
    public List<Employee> getEmployeesSortedBy(String criteria) {
        return switch (criteria.toLowerCase()) {
            case "name" -> employeeRepository.findAll().stream()
                    .sorted(EmployeeComparators.byName())
                    .collect(Collectors.toList());
            case "salary" -> employeeRepository.findAll().stream()
                    .sorted(EmployeeComparators.bySalary())
                    .collect(Collectors.toList());
            case "wage" -> employeeRepository.findAll().stream()
                    .sorted(EmployeeComparators.byTotalWage())
                    .collect(Collectors.toList());
            default -> employeeRepository.findAll();
        };
    }

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
        sb.append("Total Employees: ").append(employeeRepository.size()).append("\n\n");

        int index = 1;
        for (Employee emp : employeeRepository) {
            sb.append(index++).append(". ").append(emp.toString()).append("\n");
        }

        if (!employeeRepository.isEmpty()) {
            Employee highestPaid = findHighestPaidEmployee();
            sb.append("\nHighest Paid Employee: ").append(highestPaid.getName())
                    .append(" - $").append(highestPaid.calculateWage());
        }

        return sb.toString();
    }
}