package com.epam.port.service.port_service;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.exception.ServiceException;
import com.epam.port.service.port_logic.PortLogicAble;
import com.epam.port.service.port_logic.PortLogicImpl;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PortServiceImpl implements PortServiceAble {
    private final InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private final UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();
    private final PortLogicAble logic = new PortLogicImpl();

    private volatile String response;
    private final AtomicInteger permits = new AtomicInteger(0);
    private final ReentrantLock locker = new ReentrantLock(true);

    @Override
    public void shipActionFilter(SeaPort port, Ship ship) throws ServiceException {
        Semaphore dispatcher = ship.getDispatcher();

        try {

            dispatcher.acquire();
            info.showMessage(ship.toString());

            locker.lock();

            askOperatorForAction(port, ship);

            System.out.println();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new ServiceException(e);

        } finally {
            if (locker.getHoldCount() != 0) {
                locker.unlock();
            }
            permits.addAndGet(1);
            logic.releasePermission(permits, dispatcher);
        }


    }


    private void askOperatorForAction(SeaPort port, Ship ship) throws InterruptedException {
        MILLISECONDS.sleep(600);
        response = user.getMooringPermission(ship);

        if (response.equalsIgnoreCase("y")) {
            logic.acceptPermission(port, ship, locker);

        } else {
            info.showDeniedPermissionResult(ship);

        }
    }

}
