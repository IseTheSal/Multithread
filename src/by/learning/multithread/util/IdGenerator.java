package by.learning.multithread.util;

public class IdGenerator {
    private static int pierId = 0;
    private static int shipId = 0;
    private static int containerId = 0;

    public static int getPierId() {
        return pierId++;
    }

    public static int getShipId() {
        return shipId++;
    }

    public static int getContainerId() {
        return containerId++;
    }
}
