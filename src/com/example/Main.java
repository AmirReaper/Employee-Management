package com.example;

import com.example.exception.EmployeeNotFoundException;
import com.example.model.Employee;
import com.example.model.FullTimeEmployee;
import com.example.model.InternEmployee;
import com.example.model.PartTimeEmployee;
import com.example.model.Trainable;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System v2.1.0 ===");

        String companyName = InputHelper.readString("Enter company name: ");
        Company company = new Company(companyName);

        // Example of exception handling with custom exceptions
        try {
            // This will throw EmployeeNotFoundException
            company.getEmployee(0); // Should be empty
        } catch (EmployeeNotFoundException e) {
            System.out.println("⚠️  Expected exception: " + e.getMessage());
        }

        runEmployeeManagement(company);
    }

    private static void runEmployeeManagement(Company company) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Calculate Wage with Extra Hours");
            System.out.println("4. Find Highest Paid Employee");
            System.out.println("5. Show Statistics");
            System.out.println("6. Export Company Report"); // NEW in v2.1.0
            System.out.println("7. Manage Employee Training"); // NEW in v2.1.0
            System.out.println("8. Exit");

            int choice = InputHelper.readInt("Enter your choice (1-8): ", 1, 8);

            try {
                switch (choice) {
                    case 1:
                        addNewEmployee(company);
                        break;

                    case 2:
                        company.printAllEmployees();
                        break;

                    case 3:
                        calculateEmployeeWage(company);
                        break;

                    case 4:
                        findHighestPaidEmployee(company);
                        break;

                    case 5:
                        showStatistics(company);
                        break;

                    case 6: // NEW in v2.1.0
                        company.exportCompanyReport();
                        break;

                    case 7: // NEW in v2.1.0 - Training Management
                        manageEmployeeTraining(company);
                        break;

                    case 8:
                        running = false;
                        System.out.println("👋 Thank you for using the Employee Management System v2.1.0!");
                        break;
                }
            } catch (EmployeeNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid input: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
            }
        }
    }

    /**
     * NEW in v2.1.0 - Manage employee training sessions
     */
    private static void manageEmployeeTraining(Company company) {
        if (company.getEmployeeCount() == 0) {
            throw new EmployeeNotFoundException("No employees available for training.");
        }

        System.out.println("\n🎓 Employee Training Management");
        company.printAllEmployees();

        int empIndex = InputHelper.readInt("Select employee for training: ", 1, company.getEmployeeCount()) - 1;
        Employee selected = company.getEmployee(empIndex);

        // Check if employee is trainable
        if (!(selected instanceof Trainable)) {
            System.out.println("❌ " + selected.getName() + " cannot attend training sessions.");
            return;
        }

        Trainable trainableEmployee = (Trainable) selected;

        // Show available trainings
        System.out.println("\n📚 Available Training Topics:");
        String[] trainings = Trainable.getAvailableTrainings();
        for (int i = 0; i < trainings.length; i++) {
            System.out.println((i + 1) + ". " + trainings[i]);
        }

        int trainingChoice = InputHelper.readInt("Select training topic (1-" + trainings.length + "): ", 1, trainings.length);
        String selectedTopic = trainings[trainingChoice - 1];

        // Attend training
        trainableEmployee.attendTraining(selectedTopic);

        // Ask for custom topic
        String customTraining = InputHelper.readYesNo("Would you like to add a custom training topic? (y/n): ");
        if (customTraining.equals("yes")) {
            String customTopic = InputHelper.readString("Enter custom training topic: ");
            trainableEmployee.attendTraining(customTopic);
        }
    }

    private static void addNewEmployee(Company company) {
        String name = InputHelper.readString("Enter employee name: ");
        int baseSalary = InputHelper.readInt("Enter base salary: ",
                HRPolicy.MIN_SALARY, HRPolicy.MAX_SALARY);
        int hourlyRate = InputHelper.readInt("Enter hourly rate: ",
                HRPolicy.MIN_HOURLY_RATE, HRPolicy.MAX_HOURLY_RATE);

        System.out.println("Select type: 1=FullTime, 2=PartTime, 3=Intern");
        int type = InputHelper.readInt("Choice: ", 1, 3);

        Employee emp;
        switch (type) {
            case 1 -> emp = new FullTimeEmployee(name, baseSalary, hourlyRate);
            case 2 -> emp = new PartTimeEmployee(name, baseSalary, hourlyRate);
            case 3 -> emp = new InternEmployee(name, baseSalary, hourlyRate);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        company.addEmployee(emp);
        System.out.println("✅ Employee added successfully!");
    }

    private static void calculateEmployeeWage(Company company) {
        if (company.getEmployeeCount() == 0) {
            throw new EmployeeNotFoundException("No employees in the company.");
        }
        company.printAllEmployees();
        int empIndex = InputHelper.readInt("Select employee number: ",
                1, company.getEmployeeCount()) - 1;
        Employee selected = company.getEmployee(empIndex);
        int extraHours = InputHelper.readInt("Enter extra hours: ", 0, 100);
        System.out.println("Total Wage: $" + selected.calculateWage(extraHours));
    }

    private static void findHighestPaidEmployee(Company company) {
        Employee top = company.findHighestPaidEmployee();
        System.out.println("Highest Paid Employee: " + top.getName() +
                " with wage: $" + top.calculateWage());
    }

    private static void showStatistics(Company company) {
        System.out.println("Employees in company: " + company.getEmployeeCount());
        System.out.println("Total employees created: " + Employee.getNumberOfEmployees());
    }
}