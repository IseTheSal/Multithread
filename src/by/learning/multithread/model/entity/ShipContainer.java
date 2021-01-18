package by.learning.multithread.model.entity;

import by.learning.multithread.util.IdGenerator;

public class ShipContainer {

    private int id = IdGenerator.getContainerId();

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShipContainer that = (ShipContainer) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShipContainer{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
