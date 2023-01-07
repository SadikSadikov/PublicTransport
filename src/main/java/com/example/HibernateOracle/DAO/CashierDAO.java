package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.CashierEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CashierDAO implements DAOInterface<CashierEntity>{
    @Override
    public boolean addData(CashierEntity data) {
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
    public CashierEntity getData(int id) {
        return null;
    }

    @Override
    public boolean deleteData(int data) {
        return false;
    }

    @Override
    public void updateData(CashierEntity data) {

    }

    public CashierEntity getConnectedUser(String username, String password){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<CashierEntity> query = entityManager.createQuery("SELECT c FROM CashierEntity c WHERE c.userName = :username AND c.password = :password", CashierEntity.class );
            query.setParameter("username",username);
            query.setParameter("password",password);
            return query.getSingleResult();
        }
        catch (Exception e){
            System.err.println("Cashier not found");
            return null;
        }
    }

    public Long getNumberOfCashier() {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT count(c) FROM CashierEntity c");
            return (Long)query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);;
            return 0L;
        }
    }

    public List<String> getNamesOfCashier(){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            return entityManager.createQuery("SELECT c.userName FROM CashierEntity c",String.class).getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public int getIdWithName(String username){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT c.cashier_id FROM CashierEntity c WHERE c.userName = :username");
            query.setParameter("username",username);
            return (int) query.getSingleResult();
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public List<CashierEntity> getCashierWithDate(LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<CashierEntity> query = entityManager.createQuery("FROM CashierEntity c WHERE c.date_cashier >= :firstDate AND c.date_cashier <= :secondDate", CashierEntity.class);
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
