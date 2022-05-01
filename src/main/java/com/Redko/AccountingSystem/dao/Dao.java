package com.Redko.AccountingSystem.dao;

public interface Dao <T, H> {

    void save(T obj);

    void update(T obj);

    void delete(T obj);

    T findById(int id);

    H findAll();

}
