package by.learning.multithread.model.entity;

import by.learning.multithread.model.entity.impl.ConnectState;
import by.learning.multithread.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Thread {
    private static final Logger logger = LogManager.getLogger(Ship.class);

    private int pierId = IdGenerator.getShipId();
    private List<ShipContainer> containerList = new ArrayList<>();
    public static final int MAX_CAPACITY = 10;
    private PierState state;


    public boolean addContainer(ShipContainer shipContainer) {
        return containerList.add(shipContainer);
    }

    public ShipContainer unloadContainer(int index) {
        return containerList.remove(index);
    }

    public int containerSize() {
        return containerList.size();
    }

    @Override
    public void run() {
        this.setState(ConnectState.getInstance());
        logger.log(Level.DEBUG, "Connecting {} id", getPierId());
        state.connectShip(this);
        state.unloadShip(this);
        state.loadShip(this);
        logger.log(Level.DEBUG, "ship with id {} is disconnecting", getPierId());
        state.disconnectShip(this);
        logger.log(Level.DEBUG, "ship with id {} is disconnected", getPierId());
        logger.log(Level.DEBUG, "ship have {} containers", containerSize());
    }

    public void setState(PierState state) {
        this.state = state;
    }

    public int getPierId() {
        return pierId;
    }

    public void setPierId(int pierId) {
        this.pierId = pierId;
    }

    public void setContainerList(List<ShipContainer> containerList) {
        this.containerList = containerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (pierId != ship.pierId) return false;
        if (containerList != null ? !containerList.equals(ship.containerList) : ship.containerList != null)
            return false;
        return state != null ? state.equals(ship.state) : ship.state == null;
    }

    @Override
    public int hashCode() {
        int result = pierId;
        result = 31 * result + (containerList != null ? containerList.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("pierId=").append(pierId);
        sb.append(", containerList=").append(containerList);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
