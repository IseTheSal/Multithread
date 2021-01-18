package by.learning.multithread.model.entity.impl;

import by.learning.multithread.model.entity.PierState;
import by.learning.multithread.model.entity.Ship;
import by.learning.multithread.model.entity.ShipPort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectState implements PierState {

    private static final Logger logger = LogManager.getLogger(PierState.class);

    private static final ConnectState INSTANCE = new ConnectState();

    private ShipPort shipPort = ShipPort.getInstance();

    public static ConnectState getInstance() {
        return INSTANCE;
    }

    private ConnectState() {
    }

    @Override
    public void connectShip(Ship ship) {
        shipPort.connectShip(ship);
        logger.log(Level.INFO, "Ship is connecting to the pier");
        ship.setState(UnloadState.getInstance());
    }
}
