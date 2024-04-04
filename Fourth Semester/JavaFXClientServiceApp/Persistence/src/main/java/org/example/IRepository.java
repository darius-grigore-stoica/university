package org.example;

public interface IRepository<T> {
    void add(T entity);
    void remove(T entity);
    void update(T entity);
    T find(Integer id);
    Iterable<T> getAll();
}
