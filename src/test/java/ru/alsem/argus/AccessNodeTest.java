package ru.alsem.argus;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.AccessNodeLocation;
import ru.alsem.argus.model.ConnectionUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by SMertNIK on 26.06.2017.
 */
public class AccessNodeTest {
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


    @Test
    public void shouldCreateNodeWith3ConnectionUnits() {
        AccessNodeLocation location = new AccessNodeLocation("alabama", "uptown", "20b");
        AccessNode node = new AccessNode("firstNode", location);
        ConnectionUnit unit1 = new ConnectionUnit("firstConnector", 10);
        ConnectionUnit unit2 = new ConnectionUnit("medium", 8);
        ConnectionUnit unit3 = new ConnectionUnit("small", 5);
        node.addUnit(unit1);
        node.addUnit(unit2);
        node.addUnit(unit3);

        tx.begin();
        em.persist(node);
        tx.commit();

        assertNotNull(node.getId());
        assertThat(node.getConnectionUnits(),hasSize(3));
        assertNotNull(unit1.getCuId());
        assertNotNull(unit2.getCuId());
        assertNotNull(unit3.getCuId());

    }

    @Test
    public void shouldCreateFirstNode() {
        AccessNodeLocation location = new AccessNodeLocation("alabama", "uptown", "20b");
        AccessNode node = new AccessNode("firstNode", location);
        tx.begin();
        em.persist(node);
        tx.commit();
        assertNotNull("ID should not be null", node.getId());
        assertNotNull(node.getNodeLocation());
    }
}
