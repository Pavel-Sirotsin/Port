package com.epam.port.repository.model.port;

import com.epam.port.repository.model.pier.Pier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SeaPort {
    public static final int PIERS_AMOUNT = 3;
    private final BlockingQueue<Pier> piers = new ArrayBlockingQueue<>(PIERS_AMOUNT);
    private String name;

    public SeaPort() {
    }

    public SeaPort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockingQueue<Pier> getPiers() {
        return piers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeaPort seaPort = (SeaPort) o;

        if (name != null) {
            if (!name.equals(seaPort.name)) return false;
        } else {
            if (seaPort.name != null) return false;
        }
        return piers != null ? piers.equals(seaPort.piers) : seaPort.piers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (piers != null ? piers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SeaPort: ");
        sb.append(name);
        sb.append("\n");
        for (Pier pier : piers) {
            sb.append(pier);
            sb.append("\n");
        }
        return sb.toString();
    }
}
