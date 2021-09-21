package com.epam.port.repository.model.port_platform;

import com.epam.port.repository.model.container.Container;

import java.util.ArrayList;
import java.util.List;

public class ContainerPlatform {
    private static final int CAPACITY = 90;
    private int number;
    private List<Container> containers = new ArrayList<>(CAPACITY);

    public ContainerPlatform() {
    }

    public ContainerPlatform(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

        ContainerPlatform that = (ContainerPlatform) o;

        if (number != that.number) return false;
        if (containers != null) return containers.equals(that.containers);
        return that.containers == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (containers != null ? containers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerPlatform{" +
                "number=" + number +
                ", containers=" + containers +
                '}';
    }
}
