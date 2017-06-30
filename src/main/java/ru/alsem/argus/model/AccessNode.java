package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SMertNIK on 26.06.2017.
 */
@Entity
@Table(name = "NODE", schema = "public")
@NamedQuery(name = "findAllNodes", query = AccessNode.FIND_ALL)
public class AccessNode {

    public static final String FIND_ALL = "SELECT n FROM AccessNode n ORDER BY n.node_id DESC";
    @Id
    @SequenceGenerator(name = "node_sequence", sequenceName = "node_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "node_sequence")
    @Column(name = "NODE_ID", insertable = false, unique = true, updatable = false)
    @Access(AccessType.FIELD)
    private Integer node_id;

    @NotNull
    private String name;
//TODO: можно также хранить данные о локации с помощью @SecondaryTable
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

    public Integer getNode_id() {
        return node_id;
    }

    public void setNode_id(Integer id) {
        this.node_id = id;
    }
}
