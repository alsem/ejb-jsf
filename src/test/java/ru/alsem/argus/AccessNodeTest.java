package ru.alsem.argus;

import org.junit.Assert;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.AccessNodeLocation;
import ru.alsem.argus.model.ConnectionUnit;

import javax.persistence.Query;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by SMertNIK on 26.06.2017.
 */
public class AccessNodeTest extends EntityTest {

    @Test
    public void shouldFindFirstNode() {
        AccessNode node = em.find(AccessNode.class, 1);
        assertThat(node.getName(), is(equalTo("Baldur")));
    }

    @Test
    public void shouldCreateNodeWith3ConnectionUnits() {
        AccessNodeLocation location = new AccessNodeLocation("alabama", "uptown", "20b");
        AccessNode node = new AccessNode("firstNode", location);
        ConnectionUnit unit1 = new ConnectionUnit(1, "firstConnector", 10);
        ConnectionUnit unit2 = new ConnectionUnit(2, "medium", 8);
        ConnectionUnit unit3 = new ConnectionUnit(3, "small", 5);
        node.addUnit(unit1);
        node.addUnit(unit2);
        node.addUnit(unit3);

        tx.begin();
        em.persist(node);
        tx.commit();

        assertNotNull(node.getNode_id());
        assertThat(node.getConnectionUnits(), hasSize(3));
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
        assertNotNull("ID should not be null", node.getNode_id());
        assertNotNull(node.getNodeLocation());
    }

    @Test
    public void shouldGetTotalCOuntOfPointsWithinNode() {
        AccessNode node = em.find(AccessNode.class, 1);
        Query query = em.createNamedQuery("AccessNode.totalPoints").setParameter(1, node.getNode_id());
        Assert.assertThat(query.getSingleResult( ), is(equalTo(33L)));
    }

}
