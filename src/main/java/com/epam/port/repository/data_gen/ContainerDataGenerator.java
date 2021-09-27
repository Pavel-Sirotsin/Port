package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.container.Container;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContainerDataGenerator {
    private static final Logger logger = LogManager.getLogger(ContainerDataGenerator.class);

    private static final int AMOUNT = 50;
    private final Random random = new Random();

    private int genNumber() {
        logger.traceEntry("genNumber");
        int result = random.nextInt(10000);
        logger.traceExit(result);
        return result;
    }

    private String genCompany() {
        logger.traceEntry("genCompany");
        String[] companies = {"LG", "Samsung", "Motorola", "Pepsico", "Verizon"
                , "Mosaic", "VWGroup", "Toshiba", "Yamaha", "FoodLogistic"
                , " Walmart", "Archana", "TotalGr", "Texaco", "WorldLog", "AMD"};
        String result = companies[random.nextInt(companies.length)];
        logger.traceExit(result);
        return result;
    }

    private String genType() {
        logger.traceEntry("genType");
        String[] types = {"Dry", "Refrigerator", "Open Top", "Flatrack",
                "Tank", "Insulated", "Ventilated", "Open Deck"};
        String result = types[random.nextInt(types.length)];
        logger.traceExit(result);
        return result;
    }

    private int genWeight() {
        logger.traceEntry("genWeight");
        int[] values = {20, 40, 45};
        int result = values[random.nextInt(values.length)];
        logger.traceExit(result);
        return result;
    }

    public List<Container> generate() {
        logger.traceEntry("generate");
        return IntStream
                .range(0, AMOUNT)
                .mapToObj(i -> new Container(genNumber(), genCompany(), genType(), genWeight()))
                .collect(Collectors.toList());
    }
}
