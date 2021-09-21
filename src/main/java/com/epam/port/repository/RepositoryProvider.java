package com.epam.port.repository;


import com.epam.port.repository.data_gen_impl.ContainerDataGenerator;
import com.epam.port.repository.data_gen_impl.ContainerPlatformDataGenerator;
import com.epam.port.repository.data_gen_impl.PierDataGenerator;
import com.epam.port.repository.data_gen_impl.ShipDataGenerator;

public class RepositoryProvider {

    private static final RepositoryProvider INSTANCE = new RepositoryProvider();

    DataGeneratorAble shipGenImpl = new ShipDataGenerator();
    DataGeneratorAble pierGenImpl = new PierDataGenerator();
    DataGeneratorAble containerGenImpl = new ContainerDataGenerator();
    DataGeneratorAble storeGenImpl = new ContainerPlatformDataGenerator();


    private RepositoryProvider() {
    }

    public static RepositoryProvider getInstance() {
        return INSTANCE;
    }

}
