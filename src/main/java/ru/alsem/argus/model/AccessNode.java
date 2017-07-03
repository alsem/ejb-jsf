package ru.alsem.argus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность "Узел доступа". Содержит Коннекторы
 */
@Entity
@Table(name = "NODE", schema = "public")
@NamedQueries({

        @NamedQuery(name = "AccessNode.findAllNodes", query = "SELECT n FROM AccessNode n ORDER BY n.node_id"),
        @NamedQuery(name = "AccessNode.totalPoints", query = "SELECT SUM(n.capacity) FROM ConnectionUnit n WHERE n.node.node_id = ?1"),

})
public class AccessNode {

    @Id
    @SequenceGenerator(name = "node_sequence", sequenceName = "node_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "node_sequence")
    @Column(name = "NODE_ID", insertable = false, unique = true, updatable = false)
    private Integer node_id;

    @NotNull
    @Column(name = "name", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessNode)) return false;

        AccessNode that = (AccessNode) o;

        if (!node_id.equals(that.node_id)) return false;
        if (!name.equals(that.name)) return false;
        return nodeLocation.equals(that.nodeLocation);

    }

    @Override
    public int hashCode() {
        int result = node_id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + nodeLocation.hashCode();
        return result;
    }
}
