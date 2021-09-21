package com.epam.port.repository.data_gen_impl;

import com.epam.port.repository.DataGeneratorAble;
import com.epam.port.repository.model.port_pier.Pier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PierDataGenerator implements DataGeneratorAble {

    @Override
    public List<Pier> generate() {
        return IntStream
                .range(0, 10)
                .mapToObj(i -> new Pier(i,true))
                .collect(Collectors.toList());
    }

}
