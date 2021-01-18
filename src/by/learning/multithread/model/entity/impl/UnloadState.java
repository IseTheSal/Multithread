package by.learning.multithread.model.entity.impl;

import by.learning.multithread.model.entity.PierState;
import by.learning.multithread.model.entity.Ship;
import by.learning.multithread.model.entity.ShipPort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadState implements PierState {
    private static final Logger logger = LogManager.getLogger(UnloadState.class);

    private static final UnloadState INSTANCE = new UnloadState();

    private final ShipPort shipPort = ShipPort.getInstance();

    public static UnloadState getInstance() {
        return INSTANCE;
    }

    private UnloadState() {
    }

    @Override
    public void unloadShip(Ship ship) {

        shipPort.unloadShip(ship);
        logger.log(Level.INFO, "Ship is unloading in the pier");
        ship.setState(LoadState.getInstance());
    }
}
