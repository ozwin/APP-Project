package com.app.project.interfaces;

import java.util.List;

public interface IRepository<T,K> {
    T findByKey(K key);
    List<T> getAll();
    void insert(T entity);
    void delete(T entity);
}
