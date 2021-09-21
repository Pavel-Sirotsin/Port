package com.epam.port.repository.model.sea_port;

import com.epam.port.repository.model.port_pier.Pier;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class SeaPort {
    private String name;
    private static final int PIERS_AMOUNT = 10;
    private final Semaphore dispatcher = new Semaphore(PIERS_AMOUNT, true);
    private final List<Pier> piers = new CopyOnWriteArrayList<>();

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


    public List<Pier> getPiers() {
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
        if (piers != null) return piers.equals(seaPort.piers);
        return seaPort.piers == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (piers != null ? piers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SeaPort{" +
                "name='" + name + '\'' +
                ", piers=" + piers +
                '}';
    }
}
