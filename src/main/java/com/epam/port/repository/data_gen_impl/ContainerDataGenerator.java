package com.epam.port.repository.data_gen_impl;

import com.epam.port.repository.DataGeneratorAble;
import com.epam.port.repository.model.container.Container;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContainerDataGenerator implements DataGeneratorAble {

    private int genNumber(){
        return RANDOM.nextInt(10000);
    }

    private String genCompany(){
        String[] companies = {"LG", "Samsung", "Motorola", "Pepsico", "Verizon"
                ,"Mosaic", "VWGroup", "Toshiba", "Yamaha", "FoodLogistic"
        ," Walmart", "Archana", "TotalGr", "Texaco", "WorldLog"};
        return companies[RANDOM.nextInt(15)];
    }

    private String genType(){
        String[] types = {"Dry", "Refrigerator", "Open Top","Flatrack",
        "Tank", "Insulated", "Ventilated"};
        return types[RANDOM.nextInt(7)];
    }

    private int genWeight(){
        int[] values = {20,40,45};
        return values[RANDOM.nextInt(3)];
    }

    @Override
    public List<Container> generate() {
        return IntStream
                .range(0, 20)
                .mapToObj(i -> new Container(genNumber(),genCompany(),genType(),genWeight()))
                .collect(Collectors.toList());
    }
}
