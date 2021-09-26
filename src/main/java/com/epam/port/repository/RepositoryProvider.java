package com.epam.port.repository;


import com.epam.port.repository.data_gen.ContainerDataGenerator;
import com.epam.port.repository.data_gen.PierDataGenerator;
import com.epam.port.repository.data_gen.ShipDataGenerator;

public class RepositoryProvider {

    private static final RepositoryProvider INSTANCE = new RepositoryProvider();

    private final ShipDataGenerator shipGenImpl = new ShipDataGenerator();
    private final PierDataGenerator pierGenImpl = new PierDataGenerator();
    private final ContainerDataGenerator containerGenImpl = new ContainerDataGenerator();


    private RepositoryProvider() {
    }

    public static RepositoryProvider getInstance() {
        return INSTANCE;
    }

    public ShipDataGenerator getShipGenImpl() {
        return shipGenImpl;
    }

    public PierDataGenerator getPierGenImpl() {
        return pierGenImpl;
    }

    public ContainerDataGenerator getContainerGenImpl() {
        return containerGenImpl;
    }

}
