package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.PurchasedTicketsEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchasedTicketsDAO implements DAOInterface<PurchasedTicketsEntity>{
    @Override
    public boolean addData(PurchasedTicketsEntity data) {
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
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PurchasedTicketsEntity getData(int id) {
        return null;
    }

    @Override
    public boolean deleteData(int data) {
        return false;
    }

    @Override
    public void updateData(PurchasedTicketsEntity data) {

    }

    public List<PurchasedTicketsEntity> getPurchasedTicketsWithDate(LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<PurchasedTicketsEntity> query = entityManager.createQuery("FROM PurchasedTicketsEntity p WHERE p.date_purchasedTickets >= :firstDate AND p.date_purchasedTickets <= :secondDate", PurchasedTicketsEntity.class);
            query.setParameter("firstDate", firstDate);
            query.setParameter("secondDate",secondDate);
            return query.getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<Integer> getTotalPurchasedTickets(){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<Integer> query = entityManager.createQuery("SELECT p.numberOfTickets FROM PurchasedTicketsEntity p", Integer.class);
            return query.getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
