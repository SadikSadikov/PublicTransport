package com.example.HibernateOracle.DAO;

public interface DAOInterface<T> {

    boolean addData(T data);
    T getData(int id);
    boolean deleteData(int data);
    void updateData(T data);
}
