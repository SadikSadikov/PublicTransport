package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.MessagesEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO implements DAOInterface<MessagesEntity>{
    @Override
    public boolean addData(MessagesEntity data) {
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
    public MessagesEntity getData(int id) {
        return null;
    }

    @Override
    public boolean deleteData(int idMessage) {
        EntityTransaction transaction = null;
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("DELETE MessagesEntity m WHERE m.id_message = :idMessage");
            query.setParameter("idMessage", idMessage);
            query.executeUpdate();
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void updateData(MessagesEntity data) {

    }

    public int addAnswer(String answerText,int idMessage){
        EntityTransaction transaction = null;
        try {

            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE MessagesEntity m SET m.answer = :answerText WHERE m.id_message = :idMessage");
            query.setParameter("answerText", answerText);
            query.setParameter("idMessage", idMessage);
            query.executeUpdate();

            transaction.commit();
            return idMessage;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e);
            return 0;
        }
    }

    public List<MessagesEntity> getMessages(int idTc) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<MessagesEntity> query = entityManager.createQuery("SELECT m FROM MessagesEntity m JOIN TravelCompanyEntity tc ON tc.tc_id = m.id_tc_M WHERE m.id_tc_M = :idTc",MessagesEntity.class);
            query.setParameter("idTc", idTc);
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }


}
