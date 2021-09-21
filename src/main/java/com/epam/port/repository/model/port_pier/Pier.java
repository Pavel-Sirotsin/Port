package com.epam.port.repository.model.port_pier;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.port_platform.ContainerPlatform;
import com.epam.port.repository.model.ship.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Pier {
    private int number;
    private volatile boolean free;
    private Ship current;
    private final Exchanger<Container> reloadOperator = new Exchanger<>();
    private List<ContainerPlatform> warehouses = new ArrayList<>();

    public Pier() {
    }

    public Pier(int number, boolean free) {
        this.number = number;
        this.free = free;
    }

    public int getTheNumber() {
        return number;
    }

    public void setTheNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public List<ContainerPlatform> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<ContainerPlatform> warehouses) {
        this.warehouses = warehouses;
    }

    public Ship getCurrent() {
        return current;
    }

    public void setCurrent(Ship current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pier pier = (Pier) o;

        if (number != pier.number) return false;
        if (free != pier.free) return false;
        if (current != null) {
            if (!current.equals(pier.current)) return false;
        } else {
            if (pier.current != null) return false;
        }
        if (warehouses != null) return warehouses.equals(pier.warehouses);
        return pier.warehouses == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (free ? 1 : 0);
        result = 31 * result + (current != null ? current.hashCode() : 0);
        result = 31 * result + (warehouses != null ? warehouses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pier{" +
                "number=" + number +
                ", free=" + free +
                ", current=" + current +
                ", warehouses=" + warehouses +
                '}';
    }
}
