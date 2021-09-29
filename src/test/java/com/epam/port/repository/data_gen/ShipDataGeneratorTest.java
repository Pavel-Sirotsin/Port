package com.epam.port.repository.data_gen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ShipDataGeneratorTest {
    private final ShipDataGenerator generator = new ShipDataGenerator();

    @Test
    @DisplayName("Should return list of 10 ships")
    void shouldReturnListOfShips() {

        int expectedSize = 10;
        int actual = generator.generate().size();

        Assertions.assertEquals(expectedSize, actual);
    }
}
