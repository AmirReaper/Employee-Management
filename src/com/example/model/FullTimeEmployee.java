package com.example.model;

/**
 * Represents a full-time employee who can also attend advanced trainings
 */
public class FullTimeEmployee extends Employee implements Trainable {

    public FullTimeEmployee(String name, int baseSalary, int hourlyRate) {
        super(name, baseSalary, hourlyRate);
    }

    @Override
    public int calculateWage(int extraHours) {
        return getBaseSalary() + (getHourlyRate() * extraHours);
    }

    @Override
    public void attendTraining(String topic) {
        System.out.println("👨‍💼 " + getName() + " attended advanced training on: " + topic);
        System.out.println("📊 Training completion: " + completeTraining(topic));
    }
}