package ru.alsem.argus;

import org.junit.Before;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionPoint;
import ru.alsem.argus.model.ConnectionUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class ConnectionUnitTest extends EntityTest {

    @Test
    public void shouldFindUnitBaldur() {
        ConnectionUnit unit = em.find(ConnectionUnit.class, 1);
        assertEquals("cisco", unit.getName());
    }

    @Test
    public void shouldCreateCiscoUnitWithFivePoints() {
        AccessNode baldurNode = em.find(AccessNode.class, 1);
        ConnectionUnit ciscoUnit = new ConnectionUnit(1, "cisco", 5);
        baldurNode.addUnit(ciscoUnit);
        ciscoUnit.addPoint(new ConnectionPoint(0));
        ciscoUnit.addPoint(new ConnectionPoint(1));
        ciscoUnit.addPoint(new ConnectionPoint(2));
        ciscoUnit.addPoint(new ConnectionPoint(3));
        ciscoUnit.addPoint(new ConnectionPoint(4));
        tx.begin();
        em.persist(ciscoUnit);
        tx.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldInvalidateOversizedPointList() {
        AccessNode baldurNode = em.find(AccessNode.class, 1);
        ConnectionUnit ciscoUnit = new ConnectionUnit(1, "cisco", 5);
        baldurNode.addUnit(ciscoUnit);
        ciscoUnit.addPoint(new ConnectionPoint(0));
        ciscoUnit.addPoint(new ConnectionPoint(1));
        ciscoUnit.addPoint(new ConnectionPoint(2));
        ciscoUnit.addPoint(new ConnectionPoint(3));
        ciscoUnit.addPoint(new ConnectionPoint(4));
        ciscoUnit.addPoint(new ConnectionPoint(5));
        tx.begin();
        em.persist(ciscoUnit);
        tx.commit();
    }
}
