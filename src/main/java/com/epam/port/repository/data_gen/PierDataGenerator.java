package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PierDataGenerator {
    private static final Logger logger = LogManager.getLogger(PierDataGenerator.class);

    public List<Pier> generate() {
        logger.traceEntry("generate");
        return IntStream
                .range(0, SeaPort.PIERS_AMOUNT)
                .mapToObj(Pier::new)
                .collect(Collectors.toList());
    }

}
