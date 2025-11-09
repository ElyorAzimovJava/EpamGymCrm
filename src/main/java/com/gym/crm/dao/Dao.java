package com.gym.crm.dao;

import java.util.List;

public interface Dao<T, K> {
    T create(T entity);
    T findById(K id);
    List<T> findAll();
    T update(T entity);
    void delete(K id);
}