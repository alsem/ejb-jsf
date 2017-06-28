package ru.alsem.argus;

import org.junit.Before;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.ConnectionPointIdentifier;
import ru.alsem.argus.model.ConnectionUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class ConnectionLinkTest extends EntityTest {
    private ConnectionUnit unitOfFirstNode;
    private ConnectionUnit unitOfSecondNode;

    @Before
    public void setUp() {
        AccessNode firstNode = em.find(AccessNode.class, 1);
        AccessNode secondNode = em.find(AccessNode.class, 2);

        unitOfFirstNode = new ConnectionUnit("cisco", 10);
        unitOfSecondNode = new ConnectionUnit("d-link", 8);
        firstNode.addUnit(unitOfFirstNode);
        secondNode.addUnit(unitOfSecondNode);
        tx.begin();
        em.persist(unitOfFirstNode);
        em.persist(unitOfSecondNode);
        tx.commit();
    }

    @Test
    public void shouldCreateLink_SecondUnitFirstNode_FirstUnitSecondNode() {

        ConnectionPointIdentifier pointId1 = new ConnectionPointIdentifier(unitOfFirstNode.getCuId(), 2);
        ConnectionPointIdentifier pointId2 = new ConnectionPointIdentifier(unitOfSecondNode.getCuId(), 1);

        ConnectionLink link = new ConnectionLink(pointId1, pointId2);

        tx.begin();
        em.persist(link);
        tx.commit();
        assertEquals(link.getLinkId(), 1);
        assertThat(link.getFromPointLink().getConnectionUnit(), equalTo(unitOfFirstNode.getCuId()));
        assertThat(link.getToPointLink().getConnectionUnit(), equalTo(unitOfSecondNode.getCuId()));
    }

    @Test
    public void shouldGetStringValueOfLinkedPoints() {
        ConnectionPointIdentifier pointId1 = new ConnectionPointIdentifier(unitOfFirstNode.getCuId(), 2);
        ConnectionPointIdentifier pointId2 = new ConnectionPointIdentifier(unitOfSecondNode.getCuId(), 1);

        ConnectionLink link = new ConnectionLink(pointId1, pointId2);
        System.out.println(link.stringValue());
        assertThat(link.stringValue(),equalToIgnoringCase("link 0: 1.2 - 2.1"));
    }
}
