package com.epam.port.service.port_service;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.exception.ServiceException;
import com.epam.port.service.port_logic.PortLogicAble;
import com.epam.port.service.port_logic.PortLogicImpl;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PortServiceImpl implements PortServiceAble {
    private static final Logger logger = LogManager.getLogger(PortServiceImpl.class);
    private final InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private final UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();
    private final PortLogicAble logic = new PortLogicImpl();

    private final AtomicInteger permits = new AtomicInteger(0);
    private final ReentrantLock locker = new ReentrantLock(true);

    @Override
    public void shipActionFilter(SeaPort port, Ship ship) throws ServiceException {
        logger.traceEntry("shipActionFilter" + port.getName() + " " + ship.getName());

        logger.debug(Ship.DISPATCHER.toString());

        try {
            Ship.DISPATCHER.acquire();
            info.showMessage(ship.toString());

            locker.lock();
            logger.debug(locker.toString());

            askOperatorForAction(port, ship);

        } catch (InterruptedException | TimeoutException e) {
            Thread.currentThread().interrupt();
            logger.debug(Thread.currentThread().isInterrupted());
            logger.debug(Thread.currentThread().getState());

            throw new ServiceException(e);

        } finally {
            if (locker.getHoldCount() != 0) {
                locker.unlock();
            }

            permits.addAndGet(1);
            logger.debug(permits);

            logic.releasePermission(permits);
        }

        logger.traceExit(Ship.DISPATCHER.toString());
    }

    private void askOperatorForAction(SeaPort port, Ship ship) throws InterruptedException, TimeoutException {
        logger.traceEntry("askOperatorForAction" + port.getName() + " " + ship.getName());
        MILLISECONDS.sleep(600);

        String response = user.getMooringPermission(ship);
        logger.debug(response);

        if (response.equalsIgnoreCase("y")) {
            logic.actionWhenAccept(port, ship, locker);

        } else {
            info.showDeniedPermissionResult(ship);
        }

        logger.traceExit();
    }

}
