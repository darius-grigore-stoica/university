package com.company.repository;

import com.company.model.MyEntity;

import java.util.List;

public interface IRepository <T extends MyEntity>{
    public List<T> findAll();
    public T findById(int id);
    public T save(T entity);
    public void delete(T entity);
    public T update(T entity);
}
