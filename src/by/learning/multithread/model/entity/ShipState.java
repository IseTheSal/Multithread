package by.learning.multithread.model.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ShipState {
    Logger logger = LogManager.getLogger(ShipState.class);

    default void connectShip(Ship ship) {
        logger.log(Level.INFO, "Can`t do this right now");
    }

    default void unloadShip(Ship ship) {
        logger.log(Level.INFO, "Can`t do this right now");
    }

    default void loadShip(Ship ship) {
        logger.log(Level.INFO, "Can`t do this right now");
    }

    default void disconnectShip(Ship ship) {
        logger.log(Level.INFO, "Can`t do this right now");
    }
}
