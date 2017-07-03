package ru.alsem.argus.view.pojo;

import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.AccessNodeLocation;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Pojo для представления информации об узлах
 */
@ManagedBean(name = "nodeInfoRow")
@ViewScoped
public class AccessNodeInfoRow {

    private int id;
    private String name;
    private String region;
    private String street;
    private String house;
    private int pointsTotal;
    private int pointsFree;

    public AccessNodeInfoRow() {
    }

    public static AccessNodeInfoRow fillNodeInfo(AccessNode node, int total, int occupied) {
        AccessNodeInfoRow accessNodeInfoRow = new AccessNodeInfoRow();
        AccessNodeLocation nodeLocation = node.getNodeLocation();
        accessNodeInfoRow.setId(node.getNode_id());
        accessNodeInfoRow.setName(node.getName());
        accessNodeInfoRow.setRegion(nodeLocation.getRegion());
        accessNodeInfoRow.setStreet(nodeLocation.getStreet());
        accessNodeInfoRow.setHouse(nodeLocation.getHouseNumber());
        accessNodeInfoRow.setPointsTotal(total);
        accessNodeInfoRow.setPointsFree(total - occupied);
        return accessNodeInfoRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public void setPointsTotal(int pointsTotal) {
        this.pointsTotal = pointsTotal;
    }

    public int getPointsFree() {
        return pointsFree;
    }

    public void setPointsFree(int pointsFree) {
        this.pointsFree = pointsFree;
    }
}
