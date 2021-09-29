package com.epam.port.repository.model.ship;

import com.epam.port.repository.model.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Ship extends Thread {
    public static final int PERMIT = 5;
    public static final Semaphore DISPATCHER = new Semaphore(PERMIT, true);
    public static final Exchanger<List<Container>> EXCHANGER = new Exchanger<>();
    public static final int CAPACITY = 45;
    private List<Container> containers = new ArrayList<>(CAPACITY);
    private String owner;
    private String destination;


    public Ship(String name) {
        super(name);
    }

    public Ship(String name, String owner, String destination) {
        super(name);
        this.owner = owner;
        this.destination = destination;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (containers != null) {
            if (!containers.equals(ship.containers)) return false;
        } else {
            if (ship.containers != null) return false;
        }
        if (owner != null) {
            if (!owner.equals(ship.owner)) return false;
        } else {
            if (ship.owner != null) return false;
        }
        if (destination != null) return destination.equals(ship.destination);
        return ship.destination == null;
    }

    @Override
    public int hashCode() {
        int result = containers != null ? containers.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Ship: %-9s - Owner: %-20s - Destination: %-11s - Cargo(container): %d from %d",
                getName(), owner, destination, containers.size(), CAPACITY);
    }
}
