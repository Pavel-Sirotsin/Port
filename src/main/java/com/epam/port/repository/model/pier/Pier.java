package com.epam.port.repository.model.pier;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.ship.Ship;

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
        StringBuilder sb = new StringBuilder("Pier №:");
        sb.append(number);
        sb.append(" - Assigned ship: ");
        sb.append(shipName);
        sb.append("\n");
        sb.append("Storage platform state: ");
        sb.append(storagePlatform.size());
        sb.append(" (");
        sb.append(PLATFORM_CAPACITY);
        sb.append(")");

        return sb.toString();
    }
}
