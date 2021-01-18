package by.learning.multithread.model.entity.impl;

import by.learning.multithread.model.entity.PierState;
import by.learning.multithread.model.entity.Ship;
import by.learning.multithread.model.entity.ShipPort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadState implements PierState {
    private static final Logger logger = LogManager.getLogger(LoadState.class);

    private static final LoadState INSTANCE = new LoadState();

    private static final ShipPort shipPort = ShipPort.getInstance();

    public static LoadState getInstance() {
        return INSTANCE;
    }

    private LoadState() {
    }

    @Override
    public void loadShip(Ship ship) {
        shipPort.loadShip(ship);
        logger.log(Level.INFO, "Ship is loading in the pier");
        ship.setState(DisconnectState.getInstance());
    }
}
