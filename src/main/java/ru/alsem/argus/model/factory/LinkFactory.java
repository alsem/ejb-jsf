package ru.alsem.argus.model.factory;

import ru.alsem.argus.model.ConnectionLink;
import ru.alsem.argus.model.ConnectionPortIdentifier;
import ru.alsem.argus.model.ConnectionUnit;

public class LinkFactory {

    public ConnectionLink createLink(ConnectionUnit sourceUnit, int pointIndexFrom, ConnectionUnit targetUnit, int pointIndexTo) {
        ConnectionPortIdentifier from = new ConnectionPortIdentifier(sourceUnit.getNode().getNode_id(), sourceUnit.getUnitNumber(), pointIndexFrom);
        ConnectionPortIdentifier to = new ConnectionPortIdentifier(targetUnit.getNode().getNode_id(), targetUnit.getUnitNumber(), pointIndexTo);
        return new ConnectionLink(from, to);
    }

    /**
     * Метод создает парную связь, меняя местамми точки соединения. Здесь не сообразил, как устранить дублирование связи.
     * @param link связь, для которой нужно создать обратную
     * @return связь от конечной точки к начальной
     */
    public ConnectionLink flipped(ConnectionLink link) {
        ConnectionLink flippedLink = new ConnectionLink(link.getToPortLink(), link.getFromPortLink());
        return flippedLink;
    }
}
