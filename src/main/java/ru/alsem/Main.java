package ru.alsem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by SMertNIK on 25.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        Book book = new Book("H2G2",  "Автостопом по Галактике", 12.5F,
                "1-84023-742-2");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("argus_emf");
        EntityManager em = emf.createEntityManager();

        // 3-Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();

        System.out.println("######### " + book.getDescription());

        // 5-Closes the entity manager and the factory
        em.close();
        emf.close();

    }
}
