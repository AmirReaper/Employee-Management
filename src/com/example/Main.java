package com.example;

import com.example.exception.EmployeeNotFoundException;
import com.example.model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System v2.2.0 ===");

        String companyName = InputHelper.readString("Enter company name: ");
        Company company = new Company(companyName);

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
            System.out.println("6. Export Company Report");
            System.out.println("7. Manage Employee Training");
            System.out.println("8. Generic Features Demo"); // NEW in v2.2.0
            System.out.println("9. Advanced Employee Search"); // NEW in v2.2.0
            System.out.println("10. Exit");

            int choice = InputHelper.readInt("Enter your choice (1-10): ", 1, 10);

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

                    case 6:
                        company.exportCompanyReport();
                        break;

                    case 7:
                        manageEmployeeTraining(company);
                        break;

                    case 8: // NEW in v2.2.0
                        company.demonstrateGenericFeatures();
                        break;

                    case 9: // NEW in v2.2.0
                        advancedEmployeeSearch(company);
                        break;

                    case 10:
                        running = false;
                        System.out.println("👋 Thank you for using the Employee Management System v2.2.0!");
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
     * NEW in v2.2.0 - Advanced employee search using generics
     */
    private static void advancedEmployeeSearch(Company company) {
        System.out.println("\n🔍 Advanced Employee Search");
        System.out.println("1. Find Full-time Employees");
        System.out.println("2. Find Intern Employees");
        System.out.println("3. View Employees Sorted by Criteria");

        int searchChoice = InputHelper.readInt("Enter search option (1-3): ", 1, 3);

        switch (searchChoice) {
            case 1:
                var fullTimeEmployees = company.findEmployeesByType(FullTimeEmployee.class);
                System.out.println("\n👨‍💼 Full-time Employees (" + fullTimeEmployees.size() + "):");
                fullTimeEmployees.forEach(emp -> System.out.println("  - " + emp.getName()));
                break;

            case 2:
                var internEmployees = company.findEmployeesByType(InternEmployee.class);
                System.out.println("\n🎓 Intern Employees (" + internEmployees.size() + "):");
                internEmployees.forEach(emp -> System.out.println("  - " + emp.getName()));
                break;

            case 3:
                System.out.println("Sort by: 1=Name, 2=Salary, 3=Total Wage");
                int sortChoice = InputHelper.readInt("Choice: ", 1, 3);
                String criteria = switch (sortChoice) {
                    case 2 -> "salary";
                    case 3 -> "wage";
                    default -> "name";
                };

                var sortedEmployees = company.getEmployeesSortedBy(criteria);
                System.out.println("\n📊 Employees Sorted by " + criteria.toUpperCase() + ":");
                sortedEmployees.forEach(emp -> {
                    String info = switch (criteria) {
                        case "salary" -> emp.getName() + " - $" + emp.getBaseSalary();
                        case "wage" -> emp.getName() + " - $" + emp.calculateWage();
                        default -> emp.getName();
                    };
                    System.out.println("  - " + info);
                });
                break;
        }
    }

    private static void manageEmployeeTraining(Company company) {
        if (company.getEmployeeCount() == 0) {
            throw new EmployeeNotFoundException("No employees available for training.");
        }

        System.out.println("\n🎓 Employee Training Management");
        company.printAllEmployees();

        int empIndex = InputHelper.readInt("Select employee for training: ", 1, company.getEmployeeCount()) - 1;
        Employee selected = company.getEmployee(empIndex);

        // Check if employee is trainable
        if (!(selected instanceof Trainable trainableEmployee)) {
            System.out.println("❌ " + selected.getName() + " cannot attend training sessions.");
            return;
        }

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
            case 1 -> {
                emp = new FullTimeEmployee(name, baseSalary, hourlyRate);
                System.out.println("✅ Full-time employee created (Trainable)");
            }
            case 2 -> {
                emp = new PartTimeEmployee(name, baseSalary, hourlyRate);
                System.out.println("✅ Part-time employee created (Not Trainable)");
            }
            case 3 -> {
                emp = new InternEmployee(name, baseSalary, hourlyRate);
                System.out.println("✅ Intern employee created (Trainable)");
            }
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