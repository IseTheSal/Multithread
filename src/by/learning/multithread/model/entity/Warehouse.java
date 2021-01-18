package by.learning.multithread.model.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

public class Warehouse {

    private static final Logger logger = LogManager.getLogger(Warehouse.class);

    private static final Warehouse INSTANCE = new Warehouse();

    private static final int MAX_CONTAINER_AMOUNT = 50;
    private ArrayList<ShipContainer> containerList = new ArrayList();

    private Warehouse() {
        logger.log(Level.INFO, "Here we go");
        int amount = 50;
        for (int i = 0; i < amount; i++) {
            containerList.add(new ShipContainer());
        }
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public boolean add(ShipContainer shipContainer) {
        boolean isAdded = false;
        if (containerList.size() < MAX_CONTAINER_AMOUNT) {
            isAdded = containerList.add(shipContainer);
        }
        logger.log(Level.INFO, "Current volume : {}, maximum space : {}",
                size(), MAX_CONTAINER_AMOUNT);
        return isAdded;
    }

    public ShipContainer remove(int index) {
        return containerList.remove(index);
    }

    public Optional<ShipContainer> remove() {
        Optional<ShipContainer> container = Optional.empty();
        int index = containerList.size() - 1;
        if (index > 0) {
            container = Optional.ofNullable(containerList.remove(index));
        }
        return container;
    }

    public int size() {
        return containerList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        return containerList != null ? containerList.equals(warehouse.containerList) : warehouse.containerList == null;
    }

    @Override
    public int hashCode() {
        return containerList != null ? containerList.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("containerList=").append(containerList);
        sb.append('}');
        return sb.toString();
    }
}
