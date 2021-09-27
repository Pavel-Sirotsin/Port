package com.epam.port.service.port_logic;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.port_data.PortDataImpl;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class PortLogicImpl implements PortLogicAble {
    private static final Logger logger = LogManager.getLogger(PortLogicImpl.class);
    private final InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private final UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();

    public synchronized void releasePermission(AtomicInteger permits, Semaphore dispatcher) {
        logger.traceEntry("releasePermission" + " " + permits.get());
        if (permits.get() == PortDataImpl.PERMIT) {
            if (dispatcher.hasQueuedThreads()) {
                info.showMessage(NEW_REQUEST);
                logger.debug(permits);

                for (int i = 0; i < PortDataImpl.PERMIT; i++) {
                    dispatcher.release();
                }

                permits.set(0);
            } else {
                info.showMessage(THAT_ALL);
            }
        }
        logger.traceExit(permits.get());
    }

    @Override
    public void acceptPermission(SeaPort port, Ship ship, ReentrantLock locker) throws InterruptedException, TimeoutException {
        logger.traceEntry("acceptPermission" + port.getName() + " " + ship.getName());
        BlockingQueue<Pier> piers = port.getPiers();

        Pier currentPier = piers.take();
        currentPier.setShipName(ship.getName());

        info.showAcceptPermissionResult(ship, currentPier);

        String response = user.getUnloadingPermission(ship);
        logger.debug(response);

        if (response.equalsIgnoreCase("y")) {
            int result = reloadOnPlatform(currentPier, ship);
            logger.debug(result);
            info.showAcceptUnloadingResult(ship, currentPier, result);

        } else {
            info.showDeniedUnloadingResult(ship);
            logger.debug(Thread.currentThread().getState());

            locker.unlock();
            reloadOnShip(ship);

            logger.debug(Thread.currentThread().getState());
            info.showReloadingResult(ship);

        }
        currentPier.setShipName(EMPTY);
        piers.put(currentPier);

        logger.debug(Thread.currentThread());
        logger.traceExit();

    }

    private int reloadOnPlatform(Pier pier, Ship ship) {
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

    private void reloadOnShip(Ship ship) throws InterruptedException, TimeoutException {
        logger.traceEntry("reloadOnShip" + ship.getName());
        Exchanger<List<Container>> loader = ship.getLoader();
        logger.debug(Thread.holdsLock(loader));
        ship.setContainers(loader.exchange(ship.getContainers(), 10, TimeUnit.SECONDS));
        logger.traceExit();
    }
}
