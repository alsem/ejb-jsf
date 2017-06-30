package ru.alsem.argus.controller;

import ru.alsem.argus.model.AccessNode;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by SMertNIK on 30.06.2017.
 */
@Model
@RequestScoped
public class NodeController {

    @Inject
    private AccessNodeEJB nodeEJB;
    private List<AccessNode> nodes;
    /*
    это компонент-подложка, или управляемый компонент
    по сути, здесь хранятся все необходимые объекты для jsf представления.

     */
    public List<AccessNode> getNodes(){
        nodes =  nodeEJB.getAccessNodes();
        return nodes;
    }

}
