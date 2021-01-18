package by.learning.multithread.model.entity.impl;

import by.learning.multithread.model.entity.PierState;
import by.learning.multithread.model.entity.Ship;
import by.learning.multithread.model.entity.ShipPort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisconnectState implements PierState {
    private static final Logger logger = LogManager.getLogger(DisconnectState.class);

    private static final DisconnectState INSTANCE = new DisconnectState();

    private ShipPort shipPort = ShipPort.getInstance();

    public static DisconnectState getInstance() {
        return INSTANCE;
    }

    private DisconnectState() {
    }

    @Override
    public void disconnectShip(Ship ship) {
        shipPort.disconnectShip(ship);
        logger.log(Level.INFO, "Ship is disconnecting from the pier");
        ship.setState(null);
    }
}
