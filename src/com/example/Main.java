package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System v2.0 ===");

        String companyName = InputHelper.readString("Enter company name: ");
        Company company = new Company(companyName);

        // Example of upcasting & downcasting
        Employee emp = new InternEmployee("Ali", 2000, 50); // upcast
        if (emp instanceof InternEmployee) {
            InternEmployee pte = (InternEmployee) emp; // downcast
            pte.attendTraining("Java OOP");
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
            System.out.println("6. Exit");

            int choice = InputHelper.readInt("Enter your choice (1-6): ", 1, 6);

            switch (choice) {
                case 1:
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
                    break;

                case 2:
                    company.printAllEmployees();
                    break;

                case 3:
                    if (company.getEmployeeCount() == 0) {
                        System.out.println("❌ No employees in the company.");
                        break;
                    }
                    company.printAllEmployees();
                    int empIndex = InputHelper.readInt("Select employee number: ",
                            1, company.getEmployeeCount()) - 1;
                    Employee selected = company.getEmployee(empIndex);
                    int extraHours = InputHelper.readInt("Enter extra hours: ", 0, 100);
                    System.out.println("Total Wage: " + selected.calculateWage(extraHours));
                    break;

                case 4:
                    Employee top = company.findHighestPaidEmployee();
                    if (top != null) {
                        System.out.println("Highest Paid Employee: " + top.getName() +
                                " with wage: " + top.calculateWage());
                    }
                    break;

                case 5:
                    System.out.println("Employees in company: " + company.getEmployeeCount());
                    System.out.println("Total employees created: " + Employee.getNumberOfEmployees());
                    break;

                case 6:
                    running = false;
                    System.out.println("👋 Thank you for using the Employee Management System!");
                    break;
            }
        }
    }
}
