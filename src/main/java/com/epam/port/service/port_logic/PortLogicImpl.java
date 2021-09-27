package com.epam.port.service.port_logic;

import com.epam.port.repository.model.container.Container;
import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.port_data.PortDataImpl;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class PortLogicImpl implements PortLogicAble {
    private InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();

    private volatile String response;
    private volatile int guess = (int) (Math.random() * 10);

    public synchronized void releasePermission(AtomicInteger permits, Semaphore dispatcher) {
        if (permits.get() == PortDataImpl.PERMIT) {
            if (dispatcher.hasQueuedThreads()) {
                info.showMessage(NEW_REQUEST);

                for (int i = 0; i < PortDataImpl.PERMIT; i++) {
                    dispatcher.release();
                }

                permits.set(0);
            } else {
                info.showMessage(THAT_ALL);
            }
        }
    }

    @Override
    public void acceptPermission(SeaPort port, Ship ship, ReentrantLock locker) throws InterruptedException {
        BlockingQueue<Pier> piers = port.getPiers();

        Pier currentPier = piers.take();
        currentPier.setShipName(ship.getName());

        info.showAcceptPermissionResult(ship, currentPier);

        response = user.getUnloadingPermission(ship);

        if (response.equalsIgnoreCase("y")) {
            int result = reloadOnPlatform(currentPier, ship);
            info.showAcceptUnloadingResult(ship, currentPier, result);

        } else {
            info.showDeniedUnloadingResult(ship);

            locker.unlock();
            reloadOnShip(ship);

            info.showReloadingResult(ship);

        }
        currentPier.setShipName(EMPTY);
        piers.put(currentPier);

    }

    private int reloadOnPlatform(Pier pier, Ship ship) {
        List<Container> onPier = pier.getStoragePlatform();
        List<Container> onShip = ship.getContainers();

        int availableOnPlatform = Pier.PLATFORM_CAPACITY - onPier.size();
        int availableFromShip = onShip.size();

        Iterator<Container> iterator = onShip.iterator();

        if (availableOnPlatform > availableFromShip) {
            for (int i = 0; i < availableFromShip; i++) {
                Container temp = iterator.next();
                onPier.add(temp);
                iterator.remove();
            }

            return availableFromShip;
        } else {
            for (int i = 0; i < availableOnPlatform; i++) {
                Container temp = iterator.next();
                onPier.add(temp);
                iterator.remove();
            }

            return availableOnPlatform;
        }
    }

    private void reloadOnShip(Ship ship) throws InterruptedException {
        String name = "\"" + ship.getName() + "\"";
        Exchanger<List<Container>> loader = ship.getLoader();
        ship.setContainers(loader.exchange(ship.getContainers()));

    }
}
