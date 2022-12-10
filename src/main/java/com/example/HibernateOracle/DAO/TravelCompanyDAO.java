package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TravelCompanyDAO implements DAOInterface<TravelCompanyEntity>{

    @Override
    public boolean addData(TravelCompanyEntity data) {
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
    public TravelCompanyEntity getData(int id) {
        return null;
    }

    @Override
    public void deleteData(int data) {

    }

    @Override
    public void updateData(TravelCompanyEntity data) {

    }

    public TravelCompanyEntity getConnectedUser(String username, String password){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelCompanyEntity> query = entityManager.createQuery("SELECT t FROM TravelCompanyEntity t WHERE t.userName = :username AND t.password = :password", TravelCompanyEntity.class );
            query.setParameter("username",username);
            query.setParameter("password",password);
            return query.getSingleResult();
        }
        catch (Exception e){
            System.err.println("Travel Company not found");
            return null;
        }
    }

    public Long getNumberOfTravelCompany() {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT count(t) FROM TravelCompanyEntity t");
            return (Long)query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);;
            return 0L;
        }
    }

    public int getIdWithName(String username){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createQuery("SELECT t.tc_id FROM TravelCompanyEntity t WHERE t.userName = :username");
            query.setParameter("username",username);
            return (int) query.getSingleResult();
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public List<String> getNamesOfTC(){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            return entityManager.createQuery("SELECT t.userName FROM TravelCompanyEntity t",String.class).getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }
}
