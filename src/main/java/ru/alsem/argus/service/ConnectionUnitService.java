package ru.alsem.argus.service;

import ru.alsem.argus.model.ConnectionUnit;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Named
public class ConnectionUnitService {
    @PersistenceContext(unitName = "argus_emf")
    private EntityManager em;

    public void save(ConnectionUnit item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    public void saveAll(List<ConnectionUnit> items) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (ConnectionUnit item : items) {
            em.persist(item);
        }
        em.getTransaction().commit();
    }
}
