package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class CustomerDAO implements DAOInterface<CustomerEntity> {

    @Override
    public boolean addData(CustomerEntity data) {
        EntityTransaction transaction = null;
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(data);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if(transaction.isActive()){
                transaction.rollback();
            }
            System.out.println(e);
        }
        return false;

    }

    @Override
    public void getData(int id) {
    }

    @Override
    public CustomerEntity deleteData(CustomerEntity data) {

        return null;
    }

    @Override
    public void updateData(CustomerEntity data) {

    }

    public CustomerEntity getConnectedUser(String username,String password){
        try{
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<CustomerEntity> query = entityManager.createQuery("SELECT c FROM CustomerEntity c WHERE c.userName = :username AND c.password = :password", CustomerEntity.class );
        query.setParameter("username",username);
        query.setParameter("password",password);
        return query.getSingleResult();
        }
        catch (Exception e){
            System.err.println("Customer not found");
            return null;
        }
    }
}
