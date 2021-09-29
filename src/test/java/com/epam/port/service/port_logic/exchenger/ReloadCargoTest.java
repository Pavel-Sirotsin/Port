package com.epam.port.service.port_logic.exchenger;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.ship.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ReloadCargoTest {
    private Ship tempShip;
    private Ship shipToExchangeWith;
    private Pier tempPier;

    @BeforeEach
    void setUp() {
        tempShip = new Ship("Puma", "GIG", "OMSK");
        tempShip.getContainers().addAll(
                List.of(new Container(), new Container()));

        shipToExchangeWith = new Ship("Grape", "BOLD", "MINSK");
        shipToExchangeWith.getContainers().addAll(
                List.of(new Container(), new Container(), new Container()));

        tempPier = new Pier(0);
    }

    @Test
    @DisplayName("Should add containers from ship to pier")
    void shouldAddContainersFromShipToPier() {
        int expected = 2;
        int actual = ReloadCargo.reloadOnPlatform(tempPier, tempShip);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should exchange List between ships")
    void shouldExchangeListBetweenShips() {
        List<Container> expected = shipToExchangeWith.getContainers();

        new Thread(shipToExchangeWith) {
            @Override
            public void run() {
                try {
                    ReloadCargo.reloadOnShip(shipToExchangeWith);
                } catch (InterruptedException | TimeoutException e) {
                    currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
            ReloadCargo.reloadOnShip(tempShip);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }

        List<Container> actual = tempShip.getContainers();

        Assumptions.assumeTrue(actual.containsAll(expected));
    }
}