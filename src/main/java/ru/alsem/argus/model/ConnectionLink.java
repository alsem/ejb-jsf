package ru.alsem.argus.model;

import javax.persistence.*;
import java.text.MessageFormat;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "LINKS", schema = "public")
public class ConnectionLink {

    @Id
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @Column(name = "link_id", insertable = false, unique = true, updatable = false)
    private int linkId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="accessNode",column = @Column(name = "SOURCE_NODE_ID")),
            @AttributeOverride(name = "connectionUnitIndex", column = @Column(name = "SOURCE_UNIT_INDEX")),
            @AttributeOverride(name = "connectionPointIndex", column = @Column(name = "SOURCE_POINT_INDEX"))
    })
    private ConnectionPortIdentifier fromPortLink;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "accessNode", column = @Column(name = "DEST_NODE_ID")),
            @AttributeOverride(name = "connectionUnitIndex", column = @Column(name = "DEST_UNIT_INDEX")),
            @AttributeOverride(name = "connectionPointIndex", column = @Column(name = "DEST_POINT_INDEX"))
    })
    private ConnectionPortIdentifier toPortLink;

    public ConnectionLink(ConnectionPortIdentifier fromPointLink, ConnectionPortIdentifier toPointLink) {
        this.fromPortLink = fromPointLink;
        this.toPortLink = toPointLink;
    }

    public ConnectionLink() {

    }

    public ConnectionPortIdentifier getFromPortLink() {
        return fromPortLink;
    }

    public void setFromPortLink(ConnectionPortIdentifier sourcePointLink) {
        this.fromPortLink = sourcePointLink;
    }

    public ConnectionPortIdentifier getToPortLink() {
        return toPortLink;
    }

    public void setToPortLink(ConnectionPortIdentifier destinationPointLink) {
        this.toPortLink = destinationPointLink;
    }

    @Override
    public String toString() {
        return "ConnectionLink{" +
                "linkId=" + linkId +
                ", fromPointLink=" + fromPortLink +
                ", toPointLink=" + toPortLink +
                '}';
    }

    public String stringValue() {
        return MessageFormat.format("Связь {0}: {1} - {2}",getLinkId(), fromPortLink.stringValue(), toPortLink.stringValue());
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int link_id) {
        this.linkId = link_id;
    }
}
