package com.epam.port.repository.data_gen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ContainerDataGeneratorTest {
    private final ContainerDataGenerator generator = new ContainerDataGenerator();

    @Test
    @DisplayName("Should return a list of 50 containers")
    void shouldReturnListOfContainers() {

        int expectedSize = 50;
        int actual = generator.generate().size();

        Assertions.assertEquals(expectedSize, actual);
    }

}