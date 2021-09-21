package com.epam.port.repository.model.ship;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.sea_port.SeaPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Ship extends Thread {
    private static final int CAPACITY = 100;
    private String owner;
    private String destination;
    private SeaPort port;
    private List<Container> containers = new ArrayList<>(CAPACITY);
    private Exchanger<Container> loader;

    public Ship(String name) {
        super(name);
    }

    public Ship(String name, String owner, String destination) {
        super(name);
        this.owner = owner;
        this.destination = destination;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLine() {
        return destination;
    }

    public void setLine(String destination) {
        this.destination = destination;
    }

    public Exchanger<Container> getLoader() {
        return loader;
    }

    public void setLoader(Exchanger<Container> loader) {
        this.loader = loader;
    }

    public SeaPort getPort() {
        return port;
    }

    public void setPort(SeaPort port) {
        this.port = port;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (owner != null) {
            if (!owner.equals(ship.owner)) return false;
        } else {
            if (ship.owner != null) return false;
        }
        if (destination != null) {
            if (!destination.equals(ship.destination)) return false;
        } else {
            if (ship.destination != null) return false;
        }
        if (port != null) {
            if (!port.equals(ship.port)) return false;
        } else {
            if (ship.port != null) return false;
        }
        if (containers != null) {
            if (!containers.equals(ship.containers)) return false;
        } else {
            if (ship.containers != null) return false;
        }
        if (loader != null) return loader.equals(ship.loader);
        return ship.loader == null;
    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (containers != null ? containers.hashCode() : 0);
        result = 31 * result + (loader != null ? loader.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "owner='" + owner + '\'' +
                ", destination='" + destination + '\'' +
                ", port=" + port +
                ", containers=" + containers +
                ", loader=" + loader +
                '}';
    }
}
