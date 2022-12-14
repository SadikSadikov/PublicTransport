package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.AdminEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AdminDAO implements DAOInterface<AdminEntity>{
    @Override
    public boolean addData(AdminEntity data) {
        return false;
    }

    @Override
    public AdminEntity getData(int id) {
        return null;
    }

    @Override
    public boolean deleteData(int data) {
        return false;
    }

    @Override
    public void updateData(AdminEntity data) {

    }

    public AdminEntity getConnectedAdmin(String username, String password){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<AdminEntity> query = entityManager.createQuery("SELECT a FROM AdminEntity a WHERE a.userName = :username AND a.password = :password", AdminEntity.class );
            query.setParameter("username",username);
            query.setParameter("password",password);
            return query.getSingleResult();
        }
        catch (Exception e){
            System.err.println("Admin not found");
            return null;
        }
    }
}
