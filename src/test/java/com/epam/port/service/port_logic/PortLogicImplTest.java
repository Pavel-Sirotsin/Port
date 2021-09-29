package com.epam.port.service.port_logic;

import com.epam.port.repository.model.ship.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class PortLogicImplTest {
    private final PortLogicImpl logic = new PortLogicImpl();

    @Test
    @DisplayName("Should release permits from 0 to 5")
    void shouldReleasePermits() {
        int expected = 5;
        Ship.DISPATCHER.drainPermits();

        logic.releasePermission(new AtomicInteger(expected));
        int actual = Ship.DISPATCHER.availablePermits();

        Assertions.assertEquals(expected, actual);

    }


}
