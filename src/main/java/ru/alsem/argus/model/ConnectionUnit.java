package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "UNIT")
public class ConnectionUnit {

    @Id
    @SequenceGenerator(name = "unit_sequence", sequenceName = "unit_UNIT_ID_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_sequence")
    @Column(name = "UNIT_ID", unique = true, updatable = false, insertable = false)
    private int cuId;
    @NotNull
    private String name;
    @NotNull
    private int capacity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private AccessNode node;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<ConnectionPoint> points = new ArrayList<ConnectionPoint>();

    public ConnectionUnit() {
    }

    public ConnectionUnit(String connectionUnitName, int capacity) {
        this.name = connectionUnitName;
        this.capacity = capacity;
    }
//TODO: подумать над ограничением capacity

    public void addPoint(final ConnectionPoint... points) {
        for (ConnectionPoint point : points) {
            if (capacity == getPoints().size()) {
                System.out.println("You cannot add more points, the Unit is full");
                return;
            }
            getPoints().add(point);
            point.setUnit(this);
        }
    }

    public void removePoint(final ConnectionPoint... points) {
        for (ConnectionPoint point : points) {
            getPoints().remove(point);
            point.setUnit(null);
        }
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

    public List<ConnectionPoint> getPoints() {
        return points;
    }

    public void setPoints(List<ConnectionPoint> points) {
        this.points = points;
    }

    public int getCuId() {
        return cuId;
    }

    public void setCuId(int cuId) {
        this.cuId = cuId;
    }
}
