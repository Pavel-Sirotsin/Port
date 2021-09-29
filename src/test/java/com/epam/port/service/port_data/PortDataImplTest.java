package com.epam.port.service.port_data;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortDataImplTest {
    private final PortDataImpl impl = new PortDataImpl();
    private SeaPort actualPort;
    private List<Ship> actualList;

    @BeforeEach
    void setUp() {
        actualPort = impl.getPortWithData();
        actualList = impl.getShipWithData();
    }

    @Test
    @DisplayName("Should not return SeaPort NPE")
    void shouldNotReturnSeaPortNpe() {
        Assertions.assertNotNull(actualPort);
    }

    @Test
    @DisplayName("Should not return Ship list NPE")
    void shouldNotReturnShipListNpe() {
        Assertions.assertNotNull(actualList);
    }

    @Test
    @DisplayName("Should not return NPE on each SeaPort field")
    void shouldNotReturnNpeOnEachSeaPortField() {
        Assertions.assertAll(
                () -> assertNotNull(actualPort.getName()),
                () -> assertNotNull(actualPort.getPiers()));
    }

    @Test
    @DisplayName("Should return amount of piers equals 3")
    void shouldReturnAmountOfPiersEqualsThree() {
        int expected = SeaPort.PIERS_AMOUNT;
        int actual = actualPort.getPiers().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should not return NPE on list with ships")
    void shouldNotReturnNpeOnListWithShips() {
        Assertions.assertNotNull(actualList);
    }

    @Test
    @DisplayName("Should not return NPE on each Ship field")
    void shouldNotReturnNpeOnEachShipField() {
        actualList.forEach(ship -> assertAll(
                () -> assertNotNull(ship.getContainers()),
                () -> assertNotNull(Ship.DISPATCHER),
                () -> assertNotNull(Ship.EXCHANGER),
                () -> assertNotNull(ship.getDestination()),
                () -> assertNotNull(ship.getOwner())));
    }
}