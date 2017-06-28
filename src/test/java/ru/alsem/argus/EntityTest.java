package ru.alsem.argus;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class EntityTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("argus_emf");
    protected EntityManager em;
    protected EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (em != null) em.close();
    }
}
