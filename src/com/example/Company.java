package com.example;

import com.example.repository.EmployeeRepository;
import com.example.util.CollectionUtils;
import com.example.util.EmployeeComparators;
import com.example.exception.EmployeeNotFoundException;
import com.example.exception.ExportException;
import com.example.service.EmployeeReportExporter;
import com.example.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private final String companyName;
    private final EmployeeRepository employeeRepository;

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
        employeeRepository.add(employee);
    }

    public void printAllEmployees() {
        System.out.println("\n--- " + companyName + " Employee List ---");
        if (employeeRepository.isEmpty()) {
            System.out.println("No employees in the company.");
            return;
        }

        int index = 1;
        for (Employee emp : employeeRepository) {
            System.out.println(index++ + ". " + emp);
        }
    }

    public Employee getEmployee(int index) {
        if (index < 0 || index >= employeeRepository.size()) {
            throw new EmployeeNotFoundException("Employee not found at index: " + index);
        }
        return employeeRepository.findAll().get(index);
    }

    public Employee findHighestPaidEmployee() {
        if (employeeRepository.isEmpty()) {
            throw new EmployeeNotFoundException("No employees available for comparison.");
        }


        return CollectionUtils.findMaxEmployee(
                employeeRepository.findAll(),
                EmployeeComparators.byTotalWage()
        );
    }

    public int getEmployeeCount() {
        return employeeRepository.size();
    }

    /**
     * NEW in v2.3.0 - Comprehensive Collections Framework Demo
     */
    public void demonstrateCollectionsFramework() {
        System.out.println("\n🎯 Collections Framework Demo v2.3.0");
        System.out.println("=" .repeat(50));

        // 1. LIST OPERATIONS (Base - In use)
        System.out.println("1. 📋 List Operations (Current Implementation):");
        System.out.println("   Total employees: " + employeeRepository.size());
        System.out.println("   Employee List: " + employeeRepository.findAll().getClass().getSimpleName());

        // 2. SET OPERATIONS - Remove duplicates
        System.out.println("\n2. 🔄 Set Operations (Remove Duplicates):");
        Set<Employee> employeeSet = new HashSet<>(employeeRepository.findAll());
        System.out.println("   Original list size: " + employeeRepository.size());
        System.out.println("   Set size (no duplicates): " + employeeSet.size());
        System.out.println("   Duplicates removed: " + (employeeRepository.size() - employeeSet.size()));

        // 3. QUEUE OPERATIONS - FIFO processing
        System.out.println("\n3. 🎯 Queue Operations (FIFO Processing):");
        Queue<Employee> processingQueue = new LinkedList<>(employeeRepository.findAll());
        System.out.println("   Processing queue created with " + processingQueue.size() + " employees");

        int queueNumber = 1;
        List<Employee> processedEmployees = new ArrayList<>();
        while (!processingQueue.isEmpty()) {
            Employee nextEmployee = processingQueue.poll();
            processedEmployees.add(nextEmployee);
            System.out.println("   [" + queueNumber++ + "] Processing: " + nextEmployee.getName());
        }

        // 4. MAP OPERATIONS - Quick search
        System.out.println("\n4. 🗺️ Map Operations (Fast Lookup):");
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Map<String, List<Employee>> employeesByType = new HashMap<>();

        for (Employee emp : employeeRepository) {
            // Map with hashCode for fast search
            employeeMap.put(emp.hashCode(), emp);

            // Grouping by employee type
            String type = emp.getClass().getSimpleName();
            employeesByType.computeIfAbsent(type, k -> new ArrayList<>()).add(emp);
        }

        System.out.println("   Employee Map size: " + employeeMap.size());
        System.out.println("   Employees by type:");
        employeesByType.forEach((type, emps) ->
                System.out.println("     - " + type + ": " + emps.size() + " employees")
        );

        // 5. STREAM API OPERATIONS - Functional Programming
        System.out.println("\n5. ⚡ Stream API Operations:");

        // Filtering
        List<Employee> highSalaryEmployees = employeeRepository.findAll().stream()
                .filter(emp -> emp.getBaseSalary() > 30000)
                .collect(Collectors.toList());
        System.out.println("   High salary employees (>30K): " + highSalaryEmployees.size());

        // Mapping
        List<String> employeeNames = employeeRepository.findAll().stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("   Employee names: " + employeeNames);

        // Sorting with multiple criteria
        System.out.println("\n6. 📊 Advanced Sorting:");
        List<Employee> sortedEmployees = employeeRepository.findAll().stream()
                .sorted(Comparator
                        .comparing(Employee::getBaseSalary).reversed()
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());

        System.out.println("   Top 3 employees by salary:");
        sortedEmployees.stream().limit(3).forEach(emp ->
                System.out.println("     - " + emp.getName() + ": $" + emp.getBaseSalary())
        );

        // 7. BULK OPERATIONS
        System.out.println("\n7. 🔢 Bulk Operations:");
        double totalMonthlyCost = employeeRepository.findAll().stream()
                .mapToDouble(Employee::calculateWage)
                .sum();
        double averageSalary = employeeRepository.findAll().stream()
                .mapToInt(Employee::getBaseSalary)
                .average()
                .orElse(0.0);

        Optional<Employee> highestPaid = employeeRepository.findAll().stream()
                .max(Comparator.comparingDouble(Employee::calculateWage));

        System.out.println("   Total monthly cost: $" + totalMonthlyCost);
        System.out.println("   Average salary: $" + averageSalary);
        highestPaid.ifPresent(emp ->
                System.out.println("   Highest paid: " + emp.getName() + " - $" + emp.calculateWage())
        );

        // 8. COLLECTION STATISTICS
        System.out.println("\n8. 📈 Collection Statistics:");
        IntSummaryStatistics salaryStats = employeeRepository.findAll().stream()
                .mapToInt(Employee::getBaseSalary)
                .summaryStatistics();

        System.out.println("   Salary Statistics:");
        System.out.println("     - Count: " + salaryStats.getCount());
        System.out.println("     - Min: $" + salaryStats.getMin());
        System.out.println("     - Max: $" + salaryStats.getMax());
        System.out.println("     - Average: $" + salaryStats.getAverage());
        System.out.println("     - Sum: $" + salaryStats.getSum());
    }

    /**
     * NEW in v2.3.0 - Bulk add employees
     */
    public void addEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list cannot be null or empty");
        }

        int addedCount = 0;
        for (Employee emp : employees) {
            try {
                employeeRepository.add(emp);
                addedCount++;
            } catch (Exception e) {
                System.out.println("❌ Failed to add employee: " + emp.getName());
            }
        }
        System.out.println("✅ Added " + addedCount + " employees successfully!");
    }

    /**
     * NEW in v2.3.0 - Find employees with complex criteria
     */
    public List<Employee> findEmployeesWithCriteria(int minSalary, int maxSalary, String typeFilter) {
        return employeeRepository.findAll().stream()
                .filter(emp -> emp.getBaseSalary() >= minSalary && emp.getBaseSalary() <= maxSalary)
                .filter(emp -> typeFilter == null || typeFilter.isEmpty() ||
                        emp.getClass().getSimpleName().contains(typeFilter))
                .sorted(EmployeeComparators.bySalary().reversed())
                .collect(Collectors.toList());
    }

    /**
     * NEW in v2.3.0 - Get employee distribution by type
     */
    public Map<String, Long> getEmployeeDistribution() {
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.getClass().getSimpleName(),
                        Collectors.counting()
                ));
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