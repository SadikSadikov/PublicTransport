package com.example.HibernateOracle.DAO;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class DistributorDAOTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private DistributorDAO distributorDAO = new DistributorDAO();

    @BeforeAll
    static void setUpBeforeClass(){
        entityManagerFactory =  Persistence.createEntityManagerFactory("default");
        System.out.println("EntityManagerFactory created");
    }

    @AfterAll
    static void tearDownAfterClass(){
        entityManagerFactory.close();
        System.out.println("EntityMangerFactoryDestroyed");
    }

    @BeforeEach
    void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager created");
    }

    @AfterEach
    void tearDown(){
        entityManager.close();
        System.out.println("EntityManager closed");
    }
    @Test
    void getNumberOfDistributor() {
        System.out.println("Running testCreate...");

        assertEquals(1,distributorDAO.getNumberOfDistributor());
    }
}