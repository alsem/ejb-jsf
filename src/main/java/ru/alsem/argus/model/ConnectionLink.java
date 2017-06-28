package ru.alsem.argus.model;

import javax.persistence.*;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "LINKS")
public class ConnectionLink {

    @Id
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @Column(name = "lid", insertable = false, unique = true, updatable = false)
    private Long link_id;
    @OneToOne
    private ConnectionPoint pointOne;
    @OneToOne
    private ConnectionPoint pointTo;

    public ConnectionPoint getPointOne() {
        return pointOne;
    }

    public void setPointOne(ConnectionPoint pointOne) {
        this.pointOne = pointOne;
    }

    public ConnectionPoint getPointTo() {
        return pointTo;
    }

    public void setPointTo(ConnectionPoint pointTo) {
        this.pointTo = pointTo;
    }

    public Long getLink_id() {
        return link_id;
    }

    public void setLink_id(Long link_id) {
        this.link_id = link_id;
    }
}
