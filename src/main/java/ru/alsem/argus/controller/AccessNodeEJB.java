package ru.alsem.argus.controller;

import ru.alsem.argus.model.AccessNode;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by SMertNIK on 30.06.2017.
 */
@Stateless
@Named
public class AccessNodeEJB {

    @PersistenceContext(unitName = "argus_emf")
    private EntityManager em;

    public AccessNode findAccessNode(int id) {
        AccessNode node = em.find(AccessNode.class, id);
        return node;
    }

    public List<AccessNode> getAccessNodes() {
        TypedQuery query = em.createNamedQuery("findAllNodes", AccessNode.class);
        List<AccessNode> nodes = query.getResultList();
        return nodes;
    }
}
