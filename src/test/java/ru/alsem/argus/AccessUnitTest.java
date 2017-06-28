package ru.alsem.argus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionPoint;
import ru.alsem.argus.model.ConnectionUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class AccessUnitTest {
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
    public void shouldCreateUnitWithFivePoints() {
        AccessNode baldurNode = em.find(AccessNode.class, 1);
        ConnectionUnit ciscoUnit = new ConnectionUnit("cisco", 10);
        ConnectionPoint point1 = new ConnectionPoint(ciscoUnit, 1, null);
        ConnectionPoint point2 = new ConnectionPoint(ciscoUnit, 2, null);
        ConnectionPoint point3 = new ConnectionPoint(ciscoUnit, 3, null);
        ConnectionPoint point4 = new ConnectionPoint(ciscoUnit, 4, null);
        ConnectionPoint point5 = new ConnectionPoint(ciscoUnit, 5, null);
        baldurNode.addUnit(ciscoUnit);
        ciscoUnit.addPoint(point1, point2, point3, point4, point5);
        tx.begin();
        em.persist(ciscoUnit);
        tx.commit();
        assertThat(ciscoUnit.getPoints(), hasSize(5));
    }
}
