package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    public void getData(int id) {

    }

    @Override
    public TravelCompanyEntity deleteData(TravelCompanyEntity data) {
        return null;
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
}
