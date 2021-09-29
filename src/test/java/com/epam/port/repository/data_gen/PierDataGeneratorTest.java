package com.epam.port.repository.data_gen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class PierDataGeneratorTest {
    private final PierDataGenerator generator = new PierDataGenerator();

    @Test
    @DisplayName("Should return list of 3 Piers")
    void shouldReturnListWithPiers() {

        int expectedSize = 3;
        int actual = generator.generate().size();

        Assertions.assertEquals(expectedSize, actual);
    }
}