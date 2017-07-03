package ru.alsem.argus.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Точка соединения. Встраиваемый класс
 */
@Embeddable
@Access(AccessType.FIELD)
public class ConnectionPortIdentifier implements Serializable {
    @NotNull
    @Column(nullable = false, name = "NODE_ID")
    private int accessNode;

    @NotNull
    @Column(nullable = false, name = "UNIT_INDEX")
    @Min(value = 1)
    private int connectionUnitIndex;

    @NotNull
    @Column(nullable = false, name = "POINT_INDEX")
    @Min(value = 0)
    private int connectionPointIndex;

    public ConnectionPortIdentifier() {
    }

    public ConnectionPortIdentifier(int accessNodeId, int connectionUnitId, int connectionPointId) {
        this.accessNode = accessNodeId;
        this.connectionUnitIndex = connectionUnitId;
        this.connectionPointIndex = connectionPointId;
    }

    public int getAccessNode() {
        return accessNode;
    }

    public void setAccessNode(int accessNode) {
        this.accessNode = accessNode;
    }

    @Override
    public String toString() {
        return "ConnectionPointIdentifier{" +
                "connectionUnit=" + connectionUnitIndex +
                ", connectionPoint=" + connectionPointIndex +
                '}';
    }

    public String stringValue() {
        return connectionUnitIndex + "." + connectionPointIndex;
    }

    public int getConnectionUnitIndex() {
        return connectionUnitIndex;
    }

    public void setConnectionUnitIndex(int connectionUnit) {
        this.connectionUnitIndex = connectionUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionPortIdentifier)) return false;

        ConnectionPortIdentifier that = (ConnectionPortIdentifier) o;

        if (accessNode != that.accessNode) return false;
        if (connectionUnitIndex != that.connectionUnitIndex) return false;
        return connectionPointIndex == that.connectionPointIndex;

    }

    @Override
    public int hashCode() {
        int result = accessNode;
        result = 31 * result + connectionUnitIndex;
        result = 31 * result + connectionPointIndex;
        return result;
    }

    public int getConnectionPointIndex() {
        return connectionPointIndex;
    }

    public void setConnectionPointIndex(int connectionPoint) {
        this.connectionPointIndex = connectionPoint;
    }
}
