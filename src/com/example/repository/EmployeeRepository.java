package com.example.repository;

import com.example.model.Employee;
import com.example.exception.EmployeeNotFoundException;
import java.util.*;

/**
 * Generic repository implementation for Employee entities
 * Uses Integer as ID type (employee index or custom ID)
 */
public class EmployeeRepository implements Repository<Employee, Integer>, Iterable<Employee> {
    private final List<Employee> employees = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        employees.add(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employees.stream()
                .filter(emp -> emp.hashCode() == id) // Using hashCode as ID for demo
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public boolean exists(Integer id) {
        return employees.stream()
                .anyMatch(emp -> emp.hashCode() == id);
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }

    /**
     * Generic method with bounded type parameter
     */
    public <T extends Employee> List<T> findByType(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (type.isInstance(emp)) {
                result.add(type.cast(emp));
            }
        }
        return result;
    }

    /**
     * Generic method with wildcard for read-only operations
     */
    public double calculateTotalWages(List<? extends Employee> employeeList) {
        return employeeList.stream()
                .mapToDouble(Employee::calculateWage)
                .sum();
    }

    public int size() {
        return employees.size();
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }
}