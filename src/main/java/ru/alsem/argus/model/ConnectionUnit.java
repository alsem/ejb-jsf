package ru.alsem.argus.model;

import ru.alsem.argus.listeners.CapacityValidationListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "UNIT", schema = "public")
@EntityListeners(CapacityValidationListener.class)
public class ConnectionUnit {

    @Id
    @SequenceGenerator(name = "unit_sequence", sequenceName = "unit_UNIT_ID_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_sequence")
    @Column(name = "UNIT_ID", unique = true, updatable = false, insertable = false)
    private int cuId;

    @NotNull
    @Column(name = "UNIT_INDEX", nullable = false)
    private int unitNumber;

    @NotNull
    private String name;

    @NotNull
    private int capacity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private AccessNode node;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<ConnectionPoint> connectionPoints = new ArrayList<ConnectionPoint>(capacity);

    public ConnectionUnit() {
    }

    public ConnectionUnit(int unitNumber, String connectionUnitName, int capacity) {
        this.unitNumber = unitNumber;
        this.name = connectionUnitName;
        this.capacity = capacity;
    }

    public List<ConnectionPoint> getConnectionPoints() {
        return connectionPoints;
    }

    public void setConnectionPoints(List<ConnectionPoint> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }


    public AccessNode getNode() {
        return node;
    }

    public void setNode(AccessNode node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCuId() {
        return cuId;
    }

    public void setCuId(int cuId) {
        this.cuId = cuId;
    }

    public void addPoint(ConnectionPoint... connectionPoint) {
        for (ConnectionPoint point :
                connectionPoint) {
            connectionPoints.add(point);
            point.setUnit(this);
        }
    }

    public void removePoint(ConnectionPoint... connectionPoint) {
        for (ConnectionPoint point :
                connectionPoint) {
            getConnectionPoints().remove(point);
            point.setUnit(null);
        }
    }


}
