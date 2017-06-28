package ru.alsem.argus;

import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class ConnectionUnitTest extends EntityTest {


    @Test
    public void shouldFindUnitBaldur() {
        AccessNode node = em.find(AccessNode.class, 1);
        assertEquals("Baldur", node.getName());
    }

    @Test
    public void shouldCreateUnitWithFivePoints() {
        AccessNode baldurNode = em.find(AccessNode.class, 1);
        ConnectionUnit ciscoUnit = new ConnectionUnit("cisco", 10);
        baldurNode.addUnit(ciscoUnit);
        tx.begin();
        em.persist(ciscoUnit);
        tx.commit();
    }
}
