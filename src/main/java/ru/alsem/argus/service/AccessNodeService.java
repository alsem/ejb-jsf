package ru.alsem.argus.service;

import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.view.pojo.AccessNodeInfoRow;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для манипуляции с сущностью AccessNode
 */
@Stateless
@Named
public class AccessNodeService {

    @PersistenceContext(unitName = "argus_emf")
    private EntityManager em;

    /**
     * Метод выполняет поиск всех существующий узлов
     * @return массив узлов
     */
    public List<AccessNode> findAccessNodes() {
        TypedQuery query = em.createNamedQuery("AccessNode.findAllNodes", AccessNode.class);
        return query.getResultList();
    }

    /**
     * Метод собирает массив строк для отображения основной таблицы узлов
     * @return массив объектов, представляющих данные для строк таблицы узлов
     */
    public List<AccessNodeInfoRow> collectNodeInfo() {
        List<AccessNode> nodes = findAccessNodes();
        List<AccessNodeInfoRow> nodeInfoRows = new ArrayList();

        for (AccessNode node : nodes) {
            int total = getTotalCountOfPoints(node);
            int occupied = getCountOfOccupiedPoints(node);
            AccessNodeInfoRow bean = AccessNodeInfoRow.fillNodeInfo(node, total, occupied);
            nodeInfoRows.add(bean);
        }
        return nodeInfoRows;
    }

    /**
     Метод выполняет поиск всех задействованных точек заданного узла
      * @param node заданный узел
     * @return количество задействованных точек узла
     */
    private int getCountOfOccupiedPoints(AccessNode node) {
        Query namedQuery = em.createNamedQuery("ConnectionLink.occupiedLinks");
        namedQuery.setParameter(1, node.getNode_id());
        Object singleResult = namedQuery.getSingleResult();
        return singleResult != null ? ((Long) singleResult).intValue() : 0;
    }

    /**
     * Метод выполняет поиск общего количества точек, доступных для заданного узла
     * @param node заданный узел
     * @return общее кол-во точек узла. 0, если узел не содержит коннекторов
     */
    private int getTotalCountOfPoints(AccessNode node) {

        Query namedQuery = em.createNamedQuery("AccessNode.totalPoints");
        namedQuery.setParameter(1, node.getNode_id());

        Object singleResult = namedQuery.getSingleResult();

        int total = singleResult != null ? ((Long) singleResult).intValue() : 0;
        return total;
    }

    public void save(AccessNode item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    public void saveAll(List<AccessNode> items) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (AccessNode item : items) {
            em.persist(item);
        }
        em.getTransaction().commit();
    }
}
