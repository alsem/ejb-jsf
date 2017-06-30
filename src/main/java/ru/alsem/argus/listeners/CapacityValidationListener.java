package ru.alsem.argus.listeners;

import ru.alsem.argus.model.ConnectionUnit;

import javax.persistence.PrePersist;
import java.text.MessageFormat;

/**
 * Created by SMertNIK on 29.06.2017.
 */
public class CapacityValidationListener {
    //TODO: подумать над ограничением capacity
    @PrePersist
    public void validateCapacity(ConnectionUnit unit) {
        int size = unit.getConnectionPoints().size();
        int capacity = unit.getCapacity();
        if (size > capacity) {
            String message = MessageFormat.format("Превышена ёмкость коннектора. {0} > {1}", size, capacity);
            throw new IllegalArgumentException(message);
        }
    }
}
