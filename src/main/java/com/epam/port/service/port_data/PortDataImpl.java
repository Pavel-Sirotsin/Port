package com.epam.port.service.port_data;

import com.epam.port.repository.RepositoryProvider;
import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class PortDataImpl implements PortDataAble {
    private static final Logger logger = LogManager.getLogger(PortDataImpl.class);
    private static final Random RANDOM = new Random();
    public static final int PERMIT = 5;
    private final Exchanger<List<Container>> LOADER = new Exchanger<>();
    private final Semaphore DISPATCHER = new Semaphore(PERMIT, true);

    private final RepositoryProvider provider = RepositoryProvider.getInstance();

    @Override
    public SeaPort getPortWithData() {
        logger.traceEntry("getPortWithData");
        SeaPort port = new SeaPort(NAME);
        List<Container> generatedContainers = provider.getContainerGenImpl().generate();
        List<Pier> piers = provider.getPierGenImpl().generate();
        logger.debug(generatedContainers.isEmpty());
        logger.debug(piers.isEmpty());

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
        logger.debug(port.getPiers().size());
        logger.traceExit(port.getName());
        return port;
    }

    @Override
    public List<Ship> getShipWithData() {
        logger.traceEntry("getShipWithData");
        List<Container> generatedContainers = provider.getContainerGenImpl().generate();
        List<Ship> ships = provider.getShipGenImpl().generate();
        logger.debug(generatedContainers.isEmpty());
        logger.debug(ships.isEmpty());

        int value;
        int index;

        for (Ship s : ships) {
            value = RANDOM.nextInt(Ship.CAPACITY);
            for (int i = 0; i < value; i++) {
                index = RANDOM.nextInt(generatedContainers.size());
                s.getContainers().add(generatedContainers.get(index));
            }
            logger.debug(LOADER.toString());
            s.setLoader(LOADER);
            logger.debug(DISPATCHER.toString());
            s.setDispatcher(DISPATCHER);

        }
        logger.traceExit(ships.size());
        return ships;

    }

}
