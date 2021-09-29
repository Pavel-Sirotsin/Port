package com.epam.port.service.port_logic.exchenger;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.ship.Ship;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.epam.port.view.menu.MenuViewerImpl.logger;

public class ReloadCargo {
    public static void reloadOnShip(Ship ship) throws InterruptedException, TimeoutException {
        logger.traceEntry("reloadOnShip" + ship.getName());
        logger.debug(Thread.holdsLock(Ship.EXCHANGER));

        ship.setContainers(Ship.EXCHANGER.exchange(ship.getContainers(), 10, TimeUnit.SECONDS));

        logger.traceExit();
    }

    public static int reloadOnPlatform(Pier pier, Ship ship) {
        logger.traceEntry("reloadOnPlatform" + pier.getStoragePlatform().size());

        List<Container> onPier = pier.getStoragePlatform();
        List<Container> onShip = ship.getContainers();
        logger.debug(onPier.isEmpty());
        logger.debug(onShip.isEmpty());

        int availableOnPlatform = Pier.PLATFORM_CAPACITY - onPier.size();
        int availableFromShip = onShip.size();
        logger.debug(availableFromShip);
        logger.debug(availableFromShip);

        Iterator<Container> iterator = onShip.iterator();
        logger.debug(iterator.toString());

        if (availableOnPlatform > availableFromShip) {
            for (int i = 0; i < availableFromShip; i++) {
                Container temp = iterator.next();
                onPier.add(temp);
                iterator.remove();
            }
            logger.traceExit(availableFromShip);

            return availableFromShip;
        } else {
            for (int i = 0; i < availableOnPlatform; i++) {
                Container temp = iterator.next();
                onPier.add(temp);
                iterator.remove();
            }
            logger.traceExit(availableOnPlatform);

            return availableOnPlatform;
        }
    }
}
