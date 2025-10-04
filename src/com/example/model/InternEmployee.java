package com.example.model;

/**
 * Represents an intern with limited salary and training capabilities
 */
public class InternEmployee extends Employee implements Trainable {

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
        System.out.println("🎓 " + getName() + " attended training on: " + topic);
        System.out.println("✅ " + completeTraining(topic));
    }

    /**
     * NEW in v2.1.0 - Override default method for custom behavior
     */
    @Override
    public String completeTraining(String topic) {
        return getName() + " successfully completed " + topic + " training with distinction!";
    }
}