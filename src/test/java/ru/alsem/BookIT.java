package ru.alsem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

/**
 * Created by SMertNIK on 26.06.2017.
 */
public class BookIT {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("argus_emf");
    private EntityManager em;
    private EntityTransaction tx;

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

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldFindJavaEE7Book() throws Exception {
        Book book = em.find(Book.class, 1L);
        assertEquals("Beginning Java EE 6", book.getTitle());
    }

    @Test
    public void shouldCreateH2G2Book() throws Exception {

        // Creates an instance of book
        Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F, "1-84023-742-2");

        // Persists the book to the database
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseNullTitle() {
        boolean thrown = false;
        Book book = new Book(null, "Null title, should fail", 12.5F, "1-84023-742-2");
        try {
            tx.begin();
            em.persist(book);
            tx.commit();
        } catch (RollbackException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                thrown = true;
            }
        }
        if (!thrown) {
            Assert.fail("ConstraintViolationException not thrown");
        }

    }
}