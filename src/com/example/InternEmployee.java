package com.example;

/**
 * Represents an intern with limited salary.
 */
public class InternEmployee extends Employee implements Trainable{

    public InternEmployee(String name, int baseSalary, int hourlyRate) {
        super(name, baseSalary, hourlyRate);
    }

    @Override
    public int calculateWage(int extraHours) {
        // Interns only get hourly payment, no base salary
        return getHourlyRate() * extraHours;
    }

    @Override
    public void attendTraining(String topic) {
        System.out.println(getName() + " attended training on: " + topic);
    }
}
