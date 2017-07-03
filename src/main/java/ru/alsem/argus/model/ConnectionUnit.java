package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Сущность "Коннектор". Включается в узел доступа
 */
@Entity
@Table(name = "UNIT", schema = "public")
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
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "CAPACITY", nullable = false)
    private int capacity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private AccessNode node;

    public ConnectionUnit() {
    }

    public ConnectionUnit(int unitNumber, String connectionUnitName, int capacity) {
        this.unitNumber = unitNumber;
        this.name = connectionUnitName;
        this.capacity = capacity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionUnit)) return false;

        ConnectionUnit that = (ConnectionUnit) o;

        if (cuId != that.cuId) return false;
        if (unitNumber != that.unitNumber) return false;
        if (capacity != that.capacity) return false;
        if (!name.equals(that.name)) return false;
        return node != null ? node.equals(that.node) : that.node == null;

    }

    @Override
    public int hashCode() {
        int result = cuId;
        result = 31 * result + unitNumber;
        result = 31 * result + name.hashCode();
        result = 31 * result + capacity;
        result = 31 * result + (node != null ? node.hashCode() : 0);
        return result;
    }
}
