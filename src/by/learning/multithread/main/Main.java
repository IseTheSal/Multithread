package by.learning.multithread.main;

import by.learning.multithread.model.entity.Ship;
import by.learning.multithread.model.entity.ShipContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        List<Ship> ships = main.fillShip();
        for (int i = 0; i < ships.size() - 3; i += 3) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(ships.get(i)).start();
        }
    }

    private List<Ship> fillShip() {
        List<Ship> shipList = new ArrayList<>();
        int containerAmount = 3;
        int shipAmount = 20;
        for (int k = 0; k < shipAmount; k++) {
            Ship ship = new Ship();
            for (int i = 0; i < containerAmount; i++) {
                ship.addContainer(new ShipContainer());
            }
            shipList.add(ship);
        }
        return shipList;
    }
}
