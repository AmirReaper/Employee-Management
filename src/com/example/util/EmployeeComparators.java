package com.example.util;

import com.example.model.Employee;

import java.util.Comparator;

/**
 * Factory class for Employee comparators using generics
 */
public final class EmployeeComparators {
    private EmployeeComparators() {
    }

    public static Comparator<Employee> byName() {
        return Comparator.comparing(Employee::getName);
    }

    public static Comparator<Employee> bySalary() {
        return Comparator.comparingInt(Employee::getBaseSalary);
    }

    public static Comparator<Employee> byHourlyRate() {
        return Comparator.comparingInt(Employee::getHourlyRate);
    }

    public static Comparator<Employee> byTotalWage() {
        return Comparator.comparingDouble(Employee::calculateWage);
    }

    /**
     * Generic comparator factory method
     */
    public static <T> Comparator<T> reverseOrder(Comparator<T> comparator) {
        return comparator.reversed();

    }
}