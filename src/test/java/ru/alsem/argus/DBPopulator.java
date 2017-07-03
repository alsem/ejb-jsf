package ru.alsem.argus;

import org.junit.Ignore;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.ConnectionUnit;
import ru.alsem.argus.model.factory.LinkFactory;
import ru.alsem.argus.model.factory.UnitFactory;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * программное создание и сохранение сущностей.
 */
public class DBPopulator {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("argus_emf");
    protected EntityManager em;
    protected EntityTransaction tx;

    @Ignore
    @Test
    public void populate() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        List<AccessNode> nodeList = findAccessNodes();
        UnitFactory uf = new UnitFactory();
        uf.reset(nodeList.get(0));
        ConnectionUnit unit11 = uf.createUnit("cisco10", 10);
        ConnectionUnit unit12 = uf.createUnit("cisco8", 8);
        ConnectionUnit unit13 = uf.createUnit("cisco5", 5);

        uf.reset(nodeList.get(1));
        ConnectionUnit unit21 = uf.createUnit("d-link010", 10);
        ConnectionUnit unit22 = uf.createUnit("d-link110", 10);
        ConnectionUnit unit23 = uf.createUnit("d-link210", 10);

        uf.reset(nodeList.get(2));
        ConnectionUnit unit31 = uf.createUnit("juniper010", 10);
        ConnectionUnit unit32 = uf.createUnit("juniper110", 10);
        ConnectionUnit unit33 = uf.createUnit("juniper210", 10);

        uf.reset(nodeList.get(3));
        ConnectionUnit unit41 = uf.createUnit("siemens10", 10);
        ConnectionUnit unit42 = uf.createUnit("siemens8", 8);
        ConnectionUnit unit43 = uf.createUnit("siemens15", 15);

        saveUnits(unit11, unit12, unit13, unit21, unit22,
                unit23, unit31, unit32, unit33, unit41,
                unit42, unit43);

        LinkFactory lf = new LinkFactory();
        ConnectionLink link110_221 = lf.createLink(unit11, 0, unit22, 1);
        ConnectionLink link111_222 = lf.createLink(unit11, 1, unit22, 2);
        ConnectionLink link122_223 = lf.createLink(unit11, 2, unit22, 3);

        ConnectionLink link224_335 = lf.createLink(unit22, 4, unit33, 5);
        ConnectionLink link225_336 = lf.createLink(unit22, 5, unit33, 6);

        ConnectionLink link337_411 = lf.createLink(unit33, 7, unit41, 1);
        ConnectionLink link338_412 = lf.createLink(unit33, 8, unit41, 2);
        ConnectionLink link339_413 = lf.createLink(unit33, 9, unit41, 3);

        ConnectionLink link420_120 = lf.createLink(unit33, 0, unit43, 0);

        saveLinks(link110_221, link111_222, link122_223, link224_335,
                link225_336, link337_411, link338_412, link339_413,
                link420_120);
        if (em != null) em.close();
    }

    private List<AccessNode> findAccessNodes() {
        TypedQuery query = em.createNamedQuery("AccessNode.findAllNodes", AccessNode.class);
        return query.getResultList();
    }

    private void saveLinks(ConnectionLink... links) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        LinkFactory factory = new LinkFactory();
        for (ConnectionLink item : Arrays.asList(links)) {
            em.persist(item);
            em.persist(factory.flipped(item));
        }
        em.getTransaction().commit();
    }

    private void saveUnits(ConnectionUnit... units) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (ConnectionUnit item : Arrays.asList(units)) {
            em.persist(item);
        }
        em.getTransaction().commit();
    }

}
