package com.example.repository;

import java.util.List;

/**
 * Generic repository interface for CRUD operations
 * @param <T> the type of entity
 * @param <ID> the type of identifier
 */
public interface Repository<T, ID> {
    void add(T entity);
    T findById(ID id);
    List<T> findAll();
    void remove(T entity);
    boolean exists(ID id);
}