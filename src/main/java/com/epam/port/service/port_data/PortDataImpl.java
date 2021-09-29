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

public class PortDataImpl implements PortDataAble {
    private static final Logger logger = LogManager.getLogger(PortDataImpl.class);
    private static final Random RANDOM = new Random();

    private final RepositoryProvider provider = RepositoryProvider.getInstance();

    @Override
    public SeaPort getPortWithData() {
        logger.traceEntry("getPortWithData");

        SeaPort port = new SeaPort(NAME);
        List<Container> generatedContainers = provider.getContainerGenImpl().generate();
        List<Pier> generatedPiers = provider.getPierGenImpl().generate();

        logger.debug(generatedContainers.isEmpty());
        logger.debug(generatedPiers.isEmpty());

        int value;
        int index;

        for (Pier p : generatedPiers) {
            value = RANDOM.nextInt(Pier.PLATFORM_CAPACITY);
            for (int i = 0; i < value; i++) {
                index = RANDOM.nextInt(generatedContainers.size());
                logger.debug(index);

                p.getStoragePlatform().add(generatedContainers.get(index));
            }
        }

        port.getPiers().addAll(generatedPiers);

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
                logger.debug(index);

                s.getContainers().add(generatedContainers.get(index));
            }

        }

        logger.traceExit(ships.size());
        return ships;

    }

}
