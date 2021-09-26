package com.epam.port.service.port_service;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.exception.ServiceException;
import com.epam.port.service.port_data.PortDataImpl;
import com.epam.port.service.port_logic.PortLogicAble;
import com.epam.port.service.port_logic.PortLogicImpl;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class PortServiceImpl implements PortServiceAble {
    private InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();
    private PortLogicAble logic = new PortLogicImpl();

    private volatile String response;
    private AtomicInteger permits = new AtomicInteger(0);

    @Override
    public void shipAction(SeaPort port, Ship ship) throws ServiceException {
        Semaphore dispatcher = ship.getDispatcher();
        ReentrantLock locker = new ReentrantLock(true);

        try {

            dispatcher.acquire();

            info.showMessage(ship.toString());

            locker.lock();

            askOperatorForAction(port, ship);

            locker.unlock();

            permits.addAndGet(1);
            MILLISECONDS.sleep(1000);
            logic.releasePermission(permits, dispatcher);


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServiceException(e);
        }

    }


    private synchronized void askOperatorForAction(SeaPort port, Ship ship) throws InterruptedException {
        MILLISECONDS.sleep(1000);
        response = user.getMooringPermission(ship);

        if (response.equalsIgnoreCase("y")) {
            logic.acceptPermission(port, ship);

        } else {
            info.showMessage(DENIED_DOCK);
            info.showMessage("\"" + ship.getName() + "\"" + LEAVE_AREA);

        }

    }

}
