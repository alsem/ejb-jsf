package ru.alsem.argus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.ConnectionPortIdentifier;
import ru.alsem.argus.model.ConnectionUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by SMertNIK on 28.06.2017.
 */
public class ConnectionLinkTest extends EntityTest {
    private ConnectionUnit unitOfFirstNode;
    private ConnectionUnit unitOfSecondNode;
    private AccessNode firstNode;
    private AccessNode secondNode;

    @Before
    public void setUp() {
        firstNode = em.find(AccessNode.class, 1);
        secondNode = em.find(AccessNode.class, 2);

        unitOfFirstNode = new ConnectionUnit(1, "cisco", 10);
        unitOfSecondNode = new ConnectionUnit(2, "d-link", 8);

        firstNode.addUnit(unitOfFirstNode);
        secondNode.addUnit(unitOfSecondNode);

        tx.begin();
        em.persist(unitOfFirstNode);
        em.persist(unitOfSecondNode);
        tx.commit();
    }
    @After
    public void tearDown() {
        tx.begin();
        em.remove(unitOfFirstNode);
        em.remove(unitOfSecondNode);
        tx.commit();
    }


    @Test
    public void shouldCreateLink_SecondUnitFirstNode_FirstUnitSecondNode() {
//1.1.2
        ConnectionPortIdentifier pointId1 = new ConnectionPortIdentifier(firstNode.getNode_id(), unitOfFirstNode.getCuId(), 2);
//2.1.1
        ConnectionPortIdentifier pointId2 = new ConnectionPortIdentifier(secondNode.getNode_id(), unitOfSecondNode.getCuId(), 1);

        ConnectionLink link = new ConnectionLink(pointId1, pointId2);

        tx.begin();
        em.persist(link);
        tx.commit();
        assertEquals(link.getLinkId(), 1);

        assertThat(link.getFromPortLink().getAccessNode(), is(equalTo(1)));
        assertThat(link.getToPortLink().getAccessNode(), is(equalTo(2)));
        assertThat(link.getFromPortLink().getConnectionUnitIndex(), equalTo(unitOfFirstNode.getCuId()));
        assertThat(link.getToPortLink().getConnectionUnitIndex(), equalTo(unitOfSecondNode.getCuId()));
    }

    @Test
    public void shouldGetStringValueOfLinkedPoints() {
        ConnectionPortIdentifier pointId1 = new ConnectionPortIdentifier(firstNode.getNode_id(), unitOfFirstNode.getUnitNumber(), 2);
        ConnectionPortIdentifier pointId2 = new ConnectionPortIdentifier(secondNode.getNode_id(), unitOfSecondNode.getUnitNumber(), 1);

        ConnectionLink link = new ConnectionLink(pointId1, pointId2);
        System.out.println(link.stringValue());
        assertThat(link.stringValue(),equalToIgnoringCase("Связь 0: 1.2 - 2.1"));
    }

    @Test
    public void shouldCreateDummyData() {
        assertEquals(1,1);
    }
}
