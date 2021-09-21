package com.epam.port.repository.data_gen_impl;

import com.epam.port.repository.DataGeneratorAble;
import com.epam.port.repository.model.port_platform.ContainerPlatform;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContainerPlatformDataGenerator implements DataGeneratorAble {

    @Override
    public List<ContainerPlatform> generate() {
        return IntStream
                .range(0, 5)
                .mapToObj(i -> new ContainerPlatform(i))
                .collect(Collectors.toList());
    }
}
