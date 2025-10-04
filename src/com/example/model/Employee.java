package com.example.model;

import java.util.Objects;

/**
 * Abstract base class for all types of employees.
 */
public abstract class Employee {
    private String name;
    private int baseSalary;
    private int hourlyRate;

    private static int numberOfEmployees = 0;

    public Employee(String name, int baseSalary, int hourlyRate) {
        setName(name);
        setBaseSalary(baseSalary);
        setHourlyRate(hourlyRate);
        numberOfEmployees++;
    }

    // Abstract method (must be overridden)
    public abstract int calculateWage(int extraHours);

    public int calculateWage() {
        return calculateWage(0);
    }

    // --- Getters & Setters ---
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

    // Static counter
    public static int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                "Name='" + name + '\'' +
                ", BaseSalary=" + baseSalary +
                ", HourlyRate=" + hourlyRate +
                " }";
    }

    // Object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return baseSalary == employee.baseSalary &&
                hourlyRate == employee.hourlyRate &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseSalary, hourlyRate);
    }
}
