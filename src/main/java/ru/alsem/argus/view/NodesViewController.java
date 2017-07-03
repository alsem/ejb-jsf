package ru.alsem.argus.view;

import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.service.AccessNodeService;
import ru.alsem.argus.service.ConnectionLinkService;
import ru.alsem.argus.view.pojo.AccessNodeInfoRow;
import ru.alsem.argus.view.pojo.LinkInfoRow;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Компонент-подложка, или управляемый компонент.
 * Находит и хранит все необходимые объекты для представления.
 * Осуществляет навигацию.
 */
@ManagedBean(name = "nodeBean")
@RequestScoped
public class NodesViewController implements Serializable {

    public static final String NODE_LINKS = "viewLinks";
    public static final String HOME = "index";
    private static ResourceBundle messages = ResourceBundle.getBundle("ru.alsem.argus.Messages");
    @Inject
    private AccessNodeService nodeService;
    @Inject
    private ConnectionLinkService linkService;

    private List<AccessNodeInfoRow> nodeInfoRows;
    private AccessNodeInfoRow selectedRow;
    private List<LinkInfoRow> linkList;

    public AccessNodeInfoRow getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(AccessNodeInfoRow selectedRow) {
        this.selectedRow = selectedRow;
    }

    @PostConstruct
    public void loadNodes() {
        nodeInfoRows = nodeService.collectNodeInfo();
    }

    public List<AccessNodeInfoRow> getNodeInfoRows() {
        return nodeInfoRows;
    }

    /**
     * метод возвращает название страницы для перехода к представлению связей для выделенного узла
     *
     * @return название представления
     */
    public String viewLinks() {

        if (selectedRow == null || selectedRow.getId() == 0) {
            String title = messages.getString("view.nodesview.message.hint");
            String description = messages.getString("view.nodesview.message.select_node");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, title, description));
            return HOME;
        }

        loadLinks();

        if (linkList.isEmpty()) {
            String title = messages.getString("view.nodesview.message.warning");
            String description = messages.getString("view.nodesview.message.no_links");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, title, description));
            return HOME;
        }

        return NODE_LINKS;
    }

    /**
     * Метод выполняет поиск связей для выделенного узла
     */
    private void loadLinks() {
        List<ConnectionLink> origLinks = linkService.findAllLinks(selectedRow.getId());
        linkList = new ArrayList<>(origLinks.size());
        for (ConnectionLink link : origLinks) {
            linkList.add(LinkInfoRow.buildFromLink(link));
        }
    }

    public List<LinkInfoRow> getLinkList() {
        return linkList;
    }

    public void setLinkList(ArrayList<LinkInfoRow> linkList) {
        this.linkList = linkList;
    }

}
