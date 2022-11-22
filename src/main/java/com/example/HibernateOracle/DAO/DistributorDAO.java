package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.DistributorEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    public void getData(int id) {

    }

    @Override
    public DistributorEntity deleteData(DistributorEntity data) {
        return null;
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
}
