package com.example;

/**
 * Represents a part-time employee.
 */
public class PartTimeEmployee extends Employee implements Trainable {

    public PartTimeEmployee(String name, int baseSalary, int hourlyRate) {
        super(name, baseSalary, hourlyRate);
    }

    @Override
    public int calculateWage(int extraHours) {
        return (getBaseSalary() / 2) + (getHourlyRate() * extraHours);
    }

    @Override
    public void attendTraining(String topic) {
        System.out.println(getName() + " attended training on: " + topic);
    }
}
