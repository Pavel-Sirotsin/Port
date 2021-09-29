package com.epam.port.service.port_logic;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.port_logic.exchenger.ReloadCargo;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class PortLogicImpl implements PortLogicAble {
    private static final Logger logger = LogManager.getLogger(PortLogicImpl.class);
    private final InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private final UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();

    @Override
    public synchronized void releasePermission(AtomicInteger permits) {
        logger.traceEntry("releasePermission" + " " + permits.get());

        if (permits.get() == Ship.PERMIT) {

            if (Ship.DISPATCHER.hasQueuedThreads()) {
                logger.debug(Ship.DISPATCHER.hasQueuedThreads());
                info.showMessage(NEW_REQUEST);

            }else {
                info.showMessage(THAT_ALL);
            }

            for (int i = 0; i < Ship.PERMIT; i++) {
                Ship.DISPATCHER.release();
            }

            logger.debug(permits);
            permits.set(0);
        }

        logger.traceExit(permits.get());
    }

    @Override
    public void actionWhenAccept(SeaPort port, Ship ship, ReentrantLock locker) throws InterruptedException, TimeoutException {
        logger.traceEntry("actionWhenAccept" + port.getName() + " " + ship.getName());

        BlockingQueue<Pier> piers = port.getPiers();

        Pier currentPier = piers.take();
        currentPier.setShipName(ship.getName());

        info.showAcceptPermissionResult(ship, currentPier);
        String response = user.getUnloadingPermission(ship);
        logger.debug(response);

        if (response.equalsIgnoreCase("y")) {
            int result = ReloadCargo.reloadOnPlatform(currentPier, ship);
            logger.debug(result);

            info.showAcceptUnloadingResult(ship, currentPier, result);

        } else {
            info.showDeniedUnloadingResult(ship);
            logger.debug(Thread.currentThread().getState());

            locker.unlock();
            ReloadCargo.reloadOnShip(ship);

            logger.debug(Thread.currentThread().getState());
            info.showReloadingResult(ship);

        }
        currentPier.setShipName(EMPTY);
        piers.put(currentPier);

        logger.debug(Thread.currentThread());
        logger.traceExit();
    }




}
