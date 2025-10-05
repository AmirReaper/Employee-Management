package com.example.util;

import com.example.model.Employee;
import java.util.List;
import java.util.Comparator;

/**
 * Generic utility class for collection operations
 */
public final class CollectionUtils {
    private CollectionUtils() { }

    /**
     * Generic method with bounded type parameter - finds max element
     */
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Overloaded method for Employee with custom comparator
     */
    public static Employee findMaxEmployee(List<Employee> employees, Comparator<Employee> comparator) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list cannot be null or empty");
        }

        Employee max = employees.get(0);
        for (Employee emp : employees) {
            if (comparator.compare(emp, max) > 0) {
                max = emp;
            }
        }
        return max;
    }

    /**
     * Generic method for swapping elements
     */
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Generic method with wildcard for processing any employee type
     */
    public static double calculateTotalMonthlyCost(List<? extends Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::calculateWage)
                .sum();
    }

    /**
     * Generic method with multiple type parameters
     */
    public static <T, U> String createPairString(T first, U second) {
        return "Pair: " + first + " | " + second;
    }

    /**
     * Generic method to find max using custom comparator
     */
    public static <T> T findMaxWithComparator(List<T> list, Comparator<T> comparator) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        T max = list.get(0);
        for (T item : list) {
            if (comparator.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }
}