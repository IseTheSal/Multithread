package by.learning.multithread.model.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShipPort {

    private static final Logger logger = LogManager.getLogger(ShipPort.class);

    private static final ShipPort INSTANCE = new ShipPort();

    private static final int PIER_AMOUNT = 10;
    private List<Pier> pierList = new ArrayList<>();
    private Queue<Pier> freePier = new LinkedList<>();
    private Warehouse warehouse = Warehouse.getInstance();
    private Lock locker = new ReentrantLock();

    private ShipPort() {
        for (int i = 0; i < PIER_AMOUNT; i++) {
            freePier.offer(new Pier());
        }
    }


    public static ShipPort getInstance() {
        return INSTANCE;
    }

    public boolean isPierAvailable() {
        boolean isAvailable = ((pierList.size() < PIER_AMOUNT) && !freePier.isEmpty());
        return isAvailable;
    }

    public void connectShip(Ship ship) {
        try {
            locker.lock();
            if (isPierAvailable()) {
                Pier pier = freePier.poll();
                ship.setPierId(pier.getId());
                pierList.add(pier);
                pier.setShip(ship);
                logger.log(Level.DEBUG, "ship with {} id was connected", ship.getPierId());
            } else {
                logger.log(Level.INFO, "No available pier");
            }
        } finally {
            logger.log(Level.DEBUG, "Unlocking logger in connectShip for {}", ship.getPierId());
            locker.unlock();
        }
    }

    public void unloadShip(Ship ship) {
        try {
            locker.lock();
            int index = ship.containerSize() - 1;
            while (index > 0) {
                ShipContainer container = ship.unloadContainer(index);
                boolean isAdded = warehouse.add(container);
                if (!isAdded) {
                    break;
                }
                index--;
            }
        } finally {
            logger.log(Level.DEBUG, "Unlocking logger in unload for {}", ship.getPierId());
            locker.unlock();
        }
    }

    public void loadShip(Ship ship) {
        try {
            locker.lock();
            int i = 0;
            int size = Ship.MAX_CAPACITY;
            while (i < size - 1) {
                Optional<ShipContainer> container = warehouse.remove();
                if (container.isPresent()) {
                    ShipContainer shipContainer = container.get();
                    ship.addContainer(shipContainer);
                } else {
                    logger.log(Level.INFO, "Warehouse is empty");
                    break;
                }
                i++;
            }
        } finally {
            logger.log(Level.DEBUG, "Unlocking logger in loadship for {}", ship.getPierId());
            locker.unlock();
        }
    }

    public void disconnectShip(Ship ship) {
        try {
            locker.lock();
            Optional<Pier> pierById = getPierById(ship.getPierId());
            if (pierById.isPresent()) {
                Pier pier = pierById.get();
                pier.setShip(null);
                pierList.remove(pier);
            } else {
                logger.log(Level.INFO, "Pier with this id {} does not exist", ship.getPierId());
            }
        } finally {
            logger.log(Level.DEBUG, "Unlocking logger in disconnect for {}", ship.getPierId());
            locker.unlock();
        }
    }

    public Optional<Pier> getPierById(int id) {
        Optional<Pier> result = Optional.empty();
        int i = 0;
        while (i < pierList.size()) {
            if (pierList.get(i).getId() == id) {
                result = Optional.of(pierList.get(i));
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShipPort shipBase = (ShipPort) o;

        return pierList != null ? pierList.equals(shipBase.pierList) : shipBase.pierList == null;
    }

    @Override
    public int hashCode() {
        return pierList != null ? pierList.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShipPort{");
        sb.append("pierList=").append(pierList);
        sb.append(", freePier=").append(freePier);
        sb.append(", warehouse=").append(warehouse);
        sb.append(", locker=").append(locker);
        sb.append('}');
        return sb.toString();
    }
}
