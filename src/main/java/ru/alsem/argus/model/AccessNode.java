package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "NODE")
public class AccessNode {

    @Id
    @SequenceGenerator(name = "node_sequence", sequenceName = "node_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "node_sequence")
    @Column(name = "NODE_ID", insertable = false, unique = true, updatable = false)
    @Access(AccessType.FIELD)
    private Integer id;

    @NotNull
    private String name;

    @Embedded
    private AccessNodeLocation nodeLocation;

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL)
    private List<ConnectionUnit> connectionUnits = new ArrayList<ConnectionUnit>();

    public AccessNode() {
    }

    public AccessNode(String name, AccessNodeLocation location) {
        this.name = name;
        this.nodeLocation = location;
    }

    public AccessNodeLocation getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(AccessNodeLocation nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public List<ConnectionUnit> getConnectionUnits() {
        return connectionUnits;
    }

    public void setConnectionUnits(List<ConnectionUnit> connectionUnits) {
        this.connectionUnits = connectionUnits;
    }

    public void addUnit(ConnectionUnit connectionUnit) {
        getConnectionUnits().add(connectionUnit);
        connectionUnit.setNode(this);
    }

    public void removeUnit(ConnectionUnit connectionUnit) {
        getConnectionUnits().remove(connectionUnit);
        connectionUnit.setNode(null);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
