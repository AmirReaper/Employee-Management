package com.example.model;

/**
 * Represents a part-time employee.
 */
public class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name, int baseSalary, int hourlyRate) {
        super(name, baseSalary, hourlyRate);
    }

    @Override
    public int calculateWage(int extraHours) {
        return (getBaseSalary() / 2) + (getHourlyRate() * extraHours);
    }

}
