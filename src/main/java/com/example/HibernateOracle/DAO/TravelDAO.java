package com.example.HibernateOracle.DAO;

import com.example.HibernateOracle.Model.DistributorEntity;
import com.example.HibernateOracle.Model.TravelCompanyEntity;
import com.example.HibernateOracle.Model.TravelEntity;
import com.example.HibernateOracle.Utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO implements DAOInterface<TravelEntity>{

    @Override
    public boolean addData(TravelEntity data) {
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
    public TravelEntity getData(int id) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            return entityManager.find(TravelEntity.class,id);
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
    }

    @Override
    public void deleteData(int data) {
        EntityTransaction transaction = null;
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("DELETE TravelEntity t WHERE t.id_travel = :data");
            query.setParameter("data", data);
            query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        }

    }

    @Override
    public void updateData(TravelEntity data) {

    }
    public List<TravelEntity> getTravel(int id) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("SELECT t FROM TravelEntity t JOIN TravelCompanyEntity tc ON tc.tc_id = t.id_tc WHERE t.id_tc = :id_tc", TravelEntity.class);
            query.setParameter("id_tc",id);
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<TravelEntity> getTravels() {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            return entityManager.createQuery("FROM TravelEntity t WHERE t.id_cashier_T = 1",TravelEntity.class).getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<TravelEntity> getTravelsForCashier(int idCashier,String status) {
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.id_cashier_T = :id_cashier AND t.status = :status",TravelEntity.class);
            query.setParameter("id_cashier",idCashier);
            query.setParameter("status",status);
            return query.getResultList();
        }
        catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    public int addIdCashier(int idTravel,int idCashier) {

        EntityTransaction transaction = null;
        try {
            int result;
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE TravelEntity t SET id_cashier_T = :idCashier WHERE id_travel = :idTravel");
            query.setParameter("idCashier", idCashier);
            query.setParameter("idTravel", idTravel);
            result = query.executeUpdate();

            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e);
            return 0;
        }
    }

    public int getIdCashier(int idTravel){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.id_travel = :idTravel", TravelEntity.class );
            query.setParameter("idTravel",idTravel);
            return query.getSingleResult().getId_cashier_T();
        }
        catch (Exception e){
            System.err.println("Cashier not found");
            return 0;
        }

    }

    public boolean updateStatus(String status,int idTravel){
        EntityTransaction transaction = null;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE TravelEntity t SET t.status = :status WHERE t.id_travel = :idTravel");
            query.setParameter("status",status);
            query.setParameter("idTravel", idTravel);

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

    public int searchTickets(String typeOfTravel,LocalDate dateOfDeparture,LocalDate dateOfArrival, String startingStation,String terminalStation,String modeOfTransport,String status){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.typeOfTravel = :typeOfTravel AND t.startingStation = :startingStation AND t.terminalStation = :terminalStation AND t.dateOfDeparture = :dateOfDeparture AND t.dateOfArrival = :dateOfArrival AND t.modeOfTransport = :modeOfTransport AND t.status = :status",TravelEntity.class);
            query.setParameter("typeOfTravel",typeOfTravel);
            query.setParameter("startingStation",startingStation);
            query.setParameter("terminalStation",terminalStation);
            query.setParameter("dateOfDeparture",dateOfDeparture);
            query.setParameter("dateOfArrival",dateOfArrival);
            query.setParameter("modeOfTransport",modeOfTransport);
            query.setParameter("status",status);
            return query.getResultList().get(0).getId_travel();

        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int getTotalTickets(int idTravel){
        try{
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query =  entityManager.createQuery("FROM TravelEntity t WHERE t.id_travel = :idTravel",TravelEntity.class);
            query.setParameter("idTravel",idTravel);
            return query.getSingleResult().getTicketLimit();
        }
        catch (Exception e){
            System.out.println(e);
            return -1;
        }
    }

    public boolean updateTicketNumber(int idTravel,int numberTicket){
        EntityTransaction transaction = null;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("UPDATE TravelEntity t SET t.ticketLimit = :numberTicket WHERE t.id_travel = :idTravel");
            query.setParameter("numberTicket",numberTicket);
            query.setParameter("idTravel", idTravel);
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

    public double getPriceTicket(int idTravel){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.id_travel = :idTravel", TravelEntity.class);
            query.setParameter("idTravel", idTravel);
            return query.getSingleResult().getPriceTicket();
        }
        catch (Exception e){
            System.out.println(e);
            return -1;
        }
    }

    public List<TravelEntity> getTravelsWithDateAndTC(int idTC,LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.date_travel >= :firstDate AND t.date_travel <= :secondDate AND t.id_tc = :idTC", TravelEntity.class);
            query.setParameter("firstDate", firstDate);
            query.setParameter("secondDate",secondDate);
            query.setParameter("idTC",idTC);
            return query.getResultList();

        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<TravelEntity> getTravelsWithDate(LocalDate firstDate, LocalDate secondDate){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<TravelEntity> query = entityManager.createQuery("FROM TravelEntity t WHERE t.date_travel >= :firstDate AND t.date_travel <= :secondDate", TravelEntity.class);
            query.setParameter("firstDate", firstDate);
            query.setParameter("secondDate",secondDate);
            return query.getResultList();

        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<LocalDate> getTravelsWithUnsoldTickets(){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TypedQuery<LocalDate> query = entityManager.createQuery("SELECT t.dateOfDeparture FROM TravelEntity t WHERE t.ticketLimit != 0",LocalDate.class);
            return query.getResultList();

        }
        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

    }



}
