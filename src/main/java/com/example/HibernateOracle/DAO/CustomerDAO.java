package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.CustomerEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    public CustomerEntity getData(int id) {
        return null;
    }

    @Override
    public boolean deleteData(int data) {
        return false;
    }

    @Override
    public void updateData(CustomerEntity data) {

    }

    public List<CustomerEntity> getCustomerWithDate(LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<CustomerEntity> query = entityManager.createQuery("FROM CustomerEntity c WHERE c.date_customer >= :firstDate AND c.date_customer <= :secondDate", CustomerEntity.class);
            query.setParameter("firstDate", firstDate);
            query.setParameter("secondDate",secondDate);
            return query.getResultList();
        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
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

    public int getTotalTickets(int idCustomer){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<CustomerEntity> query = entityManager.createQuery("SELECT c FROM CustomerEntity c WHERE c.customer_id = :idCustomer", CustomerEntity.class );
            query.setParameter("idCustomer",idCustomer);
            return query.getSingleResult().getTotal_tickets();
        }
        catch (Exception e){
            System.err.println("Customer not found");
            return 0;
        }
    }

    public boolean updateTotalTickets(int totalTickets,int idCustomer){
        EntityTransaction transaction = null;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE CustomerEntity c SET c.total_tickets = :totalTickets WHERE c.customer_id = :idCustomer");
            query.setParameter("totalTickets",totalTickets);
            query.setParameter("idCustomer", idCustomer);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e);
            return false;
        }
    }

    public List<String> getNameTop5Customer(){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<String> query = entityManager.createQuery("SELECT c.userName FROM CustomerEntity c ORDER BY c.total_tickets DESC",String.class);
            return query.getResultList().subList(0,5);
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    public List<Integer> getNumberOfTicketsTop5Customer(){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<Integer> query = entityManager.createQuery("SELECT c.total_tickets FROM CustomerEntity c ORDER BY c.total_tickets DESC",Integer.class);
            return query.getResultList().subList(0,5);
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }
}
