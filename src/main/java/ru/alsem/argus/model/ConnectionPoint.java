package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by SMertNIK on 29.06.2017.
 */
@Entity
@Table(name = "UNIT_POINTS", schema ="public")
public class ConnectionPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull
    @Column(nullable = false, name = "POINT_INDEX")
    private int pointIndex;

    @ManyToOne
    @JoinColumn(name = "UNIT_ID", nullable = false)
    private ConnectionUnit unit;

    public ConnectionPoint(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    public ConnectionPoint(int pointIndex, ConnectionUnit unit) {
        this.pointIndex = pointIndex;
        this.unit = unit;
    }

    public ConnectionPoint() {

    }

    public int getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    public ConnectionUnit getUnit() {
        return unit;
    }

    public void setUnit(ConnectionUnit unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
