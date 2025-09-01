package com.example;

/**
 * Represents Employee with basic attributes and wage calculation methods.
 */
public class Employee {
    private String name;
    private int baseSalary;
    private int hourlyRate;

    // Static field to count number of employees created
    private static int numberOfEmployees = 0;

    /**
     * Constructor to initialize employee attributes.
     */
    public Employee(String name, int baseSalary, int hourlyRate) {
        setName(name);
        setBaseSalary(baseSalary);
        setHourlyRate(hourlyRate);
        numberOfEmployees++;
    }

    // --- Getters & Setters with validation ---
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("❌ Name cannot be empty.");
        this.name = name;
    }

    public int getBaseSalary() { return baseSalary; }

    public void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0)
            throw new IllegalArgumentException("❌ Salary must be greater than 0.");
        this.baseSalary = baseSalary;
    }

    public int getHourlyRate() { return hourlyRate; }

    public void setHourlyRate(int hourlyRate) {
        if (hourlyRate < 0)
            throw new IllegalArgumentException("❌ Hourly rate cannot be negative.");
        this.hourlyRate = hourlyRate;
    }

    // --- Wage Calculation Methods ---
    public int calculateWage() {
        return baseSalary;
    }

    public int calculateWage(int extraHours) {
        return baseSalary + (hourlyRate * extraHours);
    }

    // --- Static Methods ---
    public static int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    @Override
    public String toString() {
        return "Employee: " + name +
                " | Base Salary: " + baseSalary +
                " | Hourly Rate: " + hourlyRate;
    }
}
