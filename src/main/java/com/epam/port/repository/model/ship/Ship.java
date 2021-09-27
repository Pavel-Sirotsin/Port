package com.epam.port.repository.model.ship;

import com.epam.port.repository.model.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Ship extends Thread {
    public static final int CAPACITY = 15;
    private String owner;
    private String destination;
    private Semaphore dispatcher;
    private Exchanger<List<Container>> loader;
    private List<Container> containers = new ArrayList<>(CAPACITY);

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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public Exchanger<List<Container>> getLoader() {
        return loader;
    }

    public void setLoader(Exchanger<List<Container>> loader) {
        this.loader = loader;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public Semaphore getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Semaphore dispatcher) {
        this.dispatcher = dispatcher;
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
        if (dispatcher != null) {
            if (!dispatcher.equals(ship.dispatcher)) return false;
        } else {
            if (ship.dispatcher != null) return false;
        }
        if (loader != null) {
            if (!loader.equals(ship.loader)) return false;
        } else {
            if (ship.loader != null) return false;
        }
        if (containers != null) return containers.equals(ship.containers);
        return ship.containers == null;
    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        result = 31 * result + (loader != null ? loader.hashCode() : 0);
        result = 31 * result + (containers != null ? containers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Ship: %-9s - Owner: %-20s - Destination: %-11s - Cargo(container): %d from %d",
                getName(), owner, destination, containers.size(), CAPACITY);
    }
}
