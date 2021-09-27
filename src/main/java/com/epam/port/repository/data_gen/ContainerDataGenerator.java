package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.container.Container;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContainerDataGenerator {
    private static final int AMOUNT = 50;

    private int genNumber() {
        return new Random().nextInt(10000);
    }

    private String genCompany() {
        String[] companies = {"LG", "Samsung", "Motorola", "Pepsico", "Verizon"
                , "Mosaic", "VWGroup", "Toshiba", "Yamaha", "FoodLogistic"
                , " Walmart", "Archana", "TotalGr", "Texaco", "WorldLog", "AMD"};
        return companies[new Random().nextInt(16)];
    }

    private String genType() {
        String[] types = {"Dry", "Refrigerator", "Open Top", "Flatrack",
                "Tank", "Insulated", "Ventilated", "Open Deck"};
        return types[new Random().nextInt(8)];
    }

    private int genWeight() {
        int[] values = {20, 40, 45};
        return values[new Random().nextInt(3)];
    }

    public List<Container> generate() {
        return IntStream
                .range(0, AMOUNT)
                .mapToObj(i -> new Container(genNumber(), genCompany(), genType(), genWeight()))
                .collect(Collectors.toList());
    }
}
