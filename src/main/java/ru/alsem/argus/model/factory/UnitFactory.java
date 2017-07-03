package ru.alsem.argus.model.factory;

import ru.alsem.argus.model.AccessNode;
import ru.alsem.argus.model.ConnectionUnit;

/**
 * Класс отвечает за создание и связывани Коннектора Узлом, индекс Коннектора инкрементируется.
 */
public class UnitFactory {
    private int currentUnitIndex;
    private AccessNode currentNode;


    public ConnectionUnit createUnit(String name, int capacity) {
        int unitIndex = increment();
        ConnectionUnit unit = new ConnectionUnit(unitIndex, name, capacity);
        currentNode.addUnit(unit);
        return unit;
    }

    private int increment() {
        return currentUnitIndex++;
    }

    /*

     */
    public void reset(AccessNode currentNode) {
        this.currentNode = currentNode;
        currentUnitIndex = 0;
    }


}
