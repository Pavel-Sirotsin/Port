package com.epam.port.service.port_data;

import com.epam.port.repository.RepositoryProvider;
import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class PortDataImpl implements PortDataAble {
    private static final Random RANDOM = new Random();
    public static final int PERMIT = 5;
    private final Exchanger<List<Container>> LOADER = new Exchanger<>();
    private final Semaphore DISPATCHER = new Semaphore(PERMIT,true);

    private RepositoryProvider provider = RepositoryProvider.getInstance();

    @Override
    public SeaPort getPortWithData() {
        SeaPort port = new SeaPort(NAME);
        List<Container> generatedContainers = provider.getContainerGenImpl().generate();
        List<Pier> piers = provider.getPierGenImpl().generate();

        int value;
        int index;

        for (Pier p : piers) {
            value = RANDOM.nextInt(Pier.PLATFORM_CAPACITY);
            for (int i = 0; i < value; i++) {
                index = RANDOM.nextInt(generatedContainers.size());
                p.getStoragePlatform().add(generatedContainers.get(index));
            }
        }

        port.getPiers().addAll(piers);

        return port;
    }

    @Override
    public List<Ship> getShipWithData() {
        List<Container> generatedContainers = provider.getContainerGenImpl().generate();
        List<Ship> ships = provider.getShipGenImpl().generate();

        int value;
        int index;

        for (Ship s : ships) {
            value = RANDOM.nextInt(Ship.CAPACITY);
            for (int i = 0; i < value; i++) {
                index = RANDOM.nextInt(generatedContainers.size());
                s.getContainers().add(generatedContainers.get(index));
            }
            s.setLoader(LOADER);
            s.setDispatcher(DISPATCHER);

        }

        return ships;

    }

}
