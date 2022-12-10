package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Model.DistributorEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DistributorDAO implements DAOInterface<DistributorEntity>{
    @Override
    public boolean addData(DistributorEntity data) {
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
    public DistributorEntity getData(int id) {
        return null;
    }

    @Override
    public void deleteData(int data) {

    }

    @Override
    public void updateData(DistributorEntity data) {

    }

    public DistributorEntity getConnectedUser(String username, String password){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<DistributorEntity> query = entityManager.createQuery("SELECT d FROM DistributorEntity d WHERE d.userName = :username AND d.password = :password", DistributorEntity.class );
            query.setParameter("username",username);
            query.setParameter("password",password);
            return query.getSingleResult();
        }
        catch (Exception e){
            System.err.println("Distributor not found");
            return null;
        }
    }

    public Long getNumberOfDistributor() {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT count(d) FROM DistributorEntity d");
            return (Long)query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);;
            return 0L;
        }
    }

    public List<DistributorEntity> getDistributorWithDate(LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<DistributorEntity> query = entityManager.createQuery("FROM DistributorEntity d WHERE d.date_distributor >= :firstDate AND d.date_distributor <= :secondDate", DistributorEntity.class);
            query.setParameter("firstDate", firstDate);
            query.setParameter("secondDate",secondDate);
            return query.getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
