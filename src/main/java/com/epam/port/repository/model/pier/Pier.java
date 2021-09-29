package com.epam.port.repository.model.pier;

import com.epam.port.repository.model.container.Container;

import java.util.ArrayList;
import java.util.List;

public class Pier {
    public static final int PLATFORM_CAPACITY = 30;
    private int number;
    private String shipName;
    private List<Container> storagePlatform = new ArrayList<>(PLATFORM_CAPACITY);


    public Pier() {
    }

    public Pier(int number) {
        this.number = number;
    }

    public Pier(int number, String shipName, List<Container> storagePlatform) {
        this.number = number;
        this.shipName = shipName;
        this.storagePlatform = storagePlatform;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public List<Container> getStoragePlatform() {
        return storagePlatform;
    }

    public void setStoragePlatform(List<Container> storagePlatform) {
        this.storagePlatform = storagePlatform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pier pier = (Pier) o;

        if (number != pier.number) return false;
        if (shipName != null) {
            if (!shipName.equals(pier.shipName)) return false;
        } else {
            if (pier.shipName != null) return false;
        }
        if (storagePlatform != null) return storagePlatform.equals(pier.storagePlatform);
        return pier.storagePlatform == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (shipName != null ? shipName.hashCode() : 0);
        result = 31 * result + (storagePlatform != null ? storagePlatform.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Pier №: %d - Assigned ship: %s\nStorage platform state: %d (%d)"
                , number, shipName, storagePlatform.size(), PLATFORM_CAPACITY);

    }
}
