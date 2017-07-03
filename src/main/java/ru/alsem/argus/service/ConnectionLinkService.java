package ru.alsem.argus.service;


import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.factory.LinkFactory;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Named
public class ConnectionLinkService {
    @PersistenceContext(unitName = "argus_emf")
    private EntityManager em;

    public void save(ConnectionLink link) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(link);
        transaction.commit();
    }

    public void saveAll(List<ConnectionLink> items) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        LinkFactory factory = new LinkFactory();
        for (ConnectionLink item : items) {
            em.persist(item);
            em.persist(factory.flipped(item));
        }
        em.getTransaction().commit();
    }

    public List<ConnectionLink> findAllLinks(int nodeId) {
        TypedQuery query = em.createNamedQuery("ConnectionLink.findAllLinksForNode", ConnectionLink.class).setParameter(1, nodeId);
        return query.getResultList();
    }

}
