package com.example.HibernateOracle.DAO;

public interface DAOInterface<T> {

    boolean addData(T data);
    void getData(int id);
    T deleteData(T data);
    void updateData(T data);
}
