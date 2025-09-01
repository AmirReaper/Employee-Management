package com.example;

/**
 * Main class containing the interactive Employee Management System.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");

        // Create a company
        String companyName = InputHelper.readString("Enter company name: ");
        Company company = new Company(companyName);

        // Start main interactive loop
        runEmployeeManagement(company);
    }

    /**
     * Main interactive loop for Employee Management.
     */
    private static void runEmployeeManagement(Company company) {
        boolean running = true;
        while (running) {
            // --- Menu ---
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Calculate Wage with Extra Hours");
            System.out.println("4. Find Highest Paid Employee");
            System.out.println("5. Show Statistics");
            System.out.println("6. Exit");

            int choice = InputHelper.readInt("Enter your choice (1-6): ", 1, 6);

            switch (choice) {
                case 1: // Add Employee
                    try {
                        String name = InputHelper.readString("Enter employee name: ");
                        int baseSalary = InputHelper.readInt("Enter base salary (1000 - 100000): ", 1000, 100000);
                        int hourlyRate = InputHelper.readInt("Enter hourly rate (10 - 1000): ", 10, 1000);

                        Employee emp = new Employee(name, baseSalary, hourlyRate);
                        company.addEmployee(emp);
                        System.out.println("✅ Employee added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                    break;

                case 2: // View All Employees
                    company.printAllEmployees();
                    break;

                case 3: // Calculate Wage with Extra Hours
                    if (company.getEmployeeCount() == 0) {
                        System.out.println("❌ No employees in the company.");
                        break;
                    }
                    company.printAllEmployees();
                    int empIndex = InputHelper.readInt("Select employee number: ", 1, company.getEmployeeCount()) - 1;
                    Employee selected = company.getEmployee(empIndex);
                    int extraHours = InputHelper.readInt("Enter extra working hours (0-100): ", 0, 100);
                    System.out.println("Total Wage: " + selected.calculateWage(extraHours));
                    break;

                case 4: // Find Highest Employee
                    Employee top = company.findHighestPaidEmployee();
                    if (top != null) {
                        System.out.println("\nHighest Paid Employee: " + top.getName() +
                                " with base salary: " + top.calculateWage());
                    } else {
                        System.out.println("❌ No employees in the company.");
                    }
                    break;

                case 5: // Show Statistics
                    System.out.println("\n--- Company Statistics ---");
                    System.out.println("Employees in company: " + company.getEmployeeCount());
                    System.out.println("Total employees created: " + Employee.getNumberOfEmployees());
                    break;

                case 6: // Exit
                    running = false;
                    System.out.println("👋 Thank you for using the Employee Management System!");
                    break;
            }
        }
    }
}
