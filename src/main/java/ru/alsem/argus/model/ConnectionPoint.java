package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "POINT")
public class ConnectionPoint {

    @Id
    @SequenceGenerator(name = "point_sequence", sequenceName = "point_CPID_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_sequence")
    @Column(name = "CPID", insertable = false, unique = true, updatable = false)
    private Long cpId;

    @NotNull
    @Column(name = "POINTNUMBER")
    private int pointNumber;

    @OneToOne
    @JoinColumn(name = "LINK_ID")
    private ConnectionLink linkedWith;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_ID")
    private ConnectionUnit unit;

    public ConnectionPoint() {
    }

    public ConnectionPoint(ConnectionUnit unit, int pointNumber, ConnectionLink linkedWith) {
        this.pointNumber = pointNumber;
        this.linkedWith = linkedWith;
        this.unit = unit;
    }

    public ConnectionUnit getUnit() {
        return unit;
    }

    public void setUnit(ConnectionUnit unit) {
        this.unit = unit;
    }

    public ConnectionLink getLinkedWith() {
        return linkedWith;
    }

    public void setLinkedWith(ConnectionLink linkedWith) {
        this.linkedWith = linkedWith;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(int pointNumber) {
        this.pointNumber = pointNumber;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }
}
