package ru.alsem.argus.view.pojo;

import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.ConnectionPortIdentifier;

import javax.faces.bean.ManagedBean;

/**
 * Pojo для представления данных о связях указанного узла
 */
@ManagedBean(name = "link")
public class LinkInfoRow {

    private int nodeId;
    private int unitIndex;
    private int pointIndex;
    private int toNodeId;
    private int toUnitIndex;
    private int toPointIndex;
    private int linkId;

    public LinkInfoRow() {
    }

    public static LinkInfoRow buildFromLink(ConnectionLink connectionLink) {
        LinkInfoRow linkInfoRow = new LinkInfoRow();
        ConnectionPortIdentifier fromPort = connectionLink.getFromPortLink();
        ConnectionPortIdentifier targetPort = connectionLink.getToPortLink();
        linkInfoRow.setLinkId(connectionLink.getLinkId());
        linkInfoRow.setNodeId(fromPort.getAccessNode());
        linkInfoRow.setUnitIndex(fromPort.getConnectionUnitIndex());
        linkInfoRow.setPointIndex(fromPort.getConnectionPointIndex());

        linkInfoRow.setToNodeId(targetPort.getAccessNode());
        linkInfoRow.setToUnitIndex(targetPort.getConnectionUnitIndex());
        linkInfoRow.setToPointIndex(targetPort.getConnectionPointIndex());
        return linkInfoRow;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(int unitIndex) {
        this.unitIndex = unitIndex;
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(int pointIndex) {
        this.pointIndex = pointIndex;
    }

    public int getToNodeId() {
        return toNodeId;
    }

    public void setToNodeId(int toNodeId) {
        this.toNodeId = toNodeId;
    }

    public int getToUnitIndex() {
        return toUnitIndex;
    }

    public void setToUnitIndex(int toUnitIndex) {
        this.toUnitIndex = toUnitIndex;
    }

    public int getToPointIndex() {
        return toPointIndex;
    }

    public void setToPointIndex(int toPointIndex) {
        this.toPointIndex = toPointIndex;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }
}
