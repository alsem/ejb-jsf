package ru.alsem.argus.model;

import javax.persistence.*;
import java.text.MessageFormat;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "LINKS")
public class ConnectionLink {

    @Id
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @Column(name = "link_id", insertable = false, unique = true, updatable = false)
    private int linkId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "connectionUnit", column = @Column(name = "SOURCE_UNIT_ID")),
            @AttributeOverride(name = "connectionPoint", column = @Column(name = "SOURCE_POINT_INDEX"))
    })
    private ConnectionPointIdentifier fromPointLink;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "connectionUnit", column = @Column(name = "DEST_UNIT_ID")),
            @AttributeOverride(name = "connectionPoint", column = @Column(name = "DEST_POINT_INDEX"))
    })
    private ConnectionPointIdentifier toPointLink;

    public ConnectionLink(ConnectionPointIdentifier fromPointLink, ConnectionPointIdentifier toPointLink) {
        this.fromPointLink = fromPointLink;
        this.toPointLink = toPointLink;
    }

    public ConnectionLink() {

    }

    public ConnectionPointIdentifier getFromPointLink() {
        return fromPointLink;
    }

    public void setFromPointLink(ConnectionPointIdentifier sourcePointLink) {
        this.fromPointLink = sourcePointLink;
    }

    public ConnectionPointIdentifier getToPointLink() {
        return toPointLink;
    }

    public void setToPointLink(ConnectionPointIdentifier destinationPointLink) {
        this.toPointLink = destinationPointLink;
    }

    @Override
    public String toString() {
        return "ConnectionLink{" +
                "linkId=" + linkId +
                ", fromPointLink=" + fromPointLink +
                ", toPointLink=" + toPointLink +
                '}';
    }

    public String stringValue() {
        return MessageFormat.format("link {0}: {1} - {2}",getLinkId(),fromPointLink.stringValue(),toPointLink.stringValue());
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int link_id) {
        this.linkId = link_id;
    }
}
