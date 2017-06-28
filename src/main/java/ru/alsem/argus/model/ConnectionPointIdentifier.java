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
public class ConnectionPointIdentifier {
    @NotNull
    @Column(nullable = false, name = "UNIT_ID")
    @Min(value = 1)
    private int connectionUnit;
    @NotNull
    @Column(nullable = false, name = "POINT_INDEX")
    private int connectionPoint;

    public ConnectionPointIdentifier() {
    }

    public ConnectionPointIdentifier(int connectionUnitId, int connectionPointId) {
        this.connectionUnit = connectionUnitId;
        this.connectionPoint = connectionPointId;
    }

    @Override
    public String toString() {
        return "ConnectionPointIdentifier{" +
                "connectionUnit=" + connectionUnit +
                ", connectionPoint=" + connectionPoint +
                '}';
    }

    public String stringValue() {
        return connectionUnit + "." + connectionPoint;
    }

    public int getConnectionUnit() {
        return connectionUnit;
    }

    public void setConnectionUnit(int connectionUnit) {
        this.connectionUnit = connectionUnit;
    }

    public int getConnectionPoint() {
        return connectionPoint;
    }

    public void setConnectionPoint(int connectionPoint) {
        this.connectionPoint = connectionPoint;
    }
}
