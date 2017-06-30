package ru.alsem.argus.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by SMertNIK on 28.06.2017.
 */
@Embeddable
@Access(AccessType.FIELD)
public class ConnectionPortIdentifier {
    @NotNull
    @Column(nullable = false, name = "NODE_ID")
    private int accessNode;

    @NotNull
    @Column(nullable = false, name = "UNIT_INDEX")
    @Min(value = 1)
    private int connectionUnitIndex;

    @NotNull
    @Column(nullable = false, name = "POINT_INDEX")
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

    public int getConnectionPointIndex() {
        return connectionPointIndex;
    }

    public void setConnectionPointIndex(int connectionPoint) {
        this.connectionPointIndex = connectionPoint;
    }
}
