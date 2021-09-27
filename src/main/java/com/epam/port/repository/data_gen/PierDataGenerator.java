package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PierDataGenerator {

    public List<Pier> generate() {
        return IntStream
                .range(0, SeaPort.PIERS_AMOUNT)
                .mapToObj(Pier::new)
                .collect(Collectors.toList());
    }

}
