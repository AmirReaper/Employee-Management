package com.example;

/**
 * Represents a full-time employee.
 */
public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, int baseSalary, int hourlyRate) {
        super(name, baseSalary, hourlyRate);
    }

    @Override
    public int calculateWage(int extraHours) {
        return getBaseSalary() + (getHourlyRate() * extraHours);
    }
}
