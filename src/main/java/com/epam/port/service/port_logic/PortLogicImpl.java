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

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PortLogicImpl implements PortLogicAble {
    private InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();

    private volatile String response;

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
    public void acceptPermission(SeaPort port, Ship ship) throws InterruptedException {
        BlockingQueue<Pier> piers = port.getPiers();
        String name = "\"" + ship.getName() + "\"";
        int result = 0;

        Pier currentPier = piers.take();
        currentPier.setShipName(ship.getName());

        MILLISECONDS.sleep(1000);
        info.showMessage(name + MOVE_UNLOAD + currentPier.toString());
        MILLISECONDS.sleep(500);
        info.showMessage(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")");
        MILLISECONDS.sleep(500);

        response = user.getUnloadingPermission(ship);

        if (response.equalsIgnoreCase("y")) {
            result = reloadOnPlatform(currentPier, ship);
            MILLISECONDS.sleep(500);
            info.showMessage(SUCCEED);
            info.showMessage(DONE + result);
            MILLISECONDS.sleep(500);
            info.showMessage(currentPier.toString());
            MILLISECONDS.sleep(500);
            info.showMessage(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")\n");
            MILLISECONDS.sleep(500);
            info.showMessage(name + LEAVE_AREA);
            MILLISECONDS.sleep(1000);

        } else {
            SECONDS.sleep(1);
            info.showMessage(DENIED_UNLOAD);
            info.showMessage(name + LEAVE_AREA);

        }

        currentPier.setShipName(EMPTY);
        piers.offer(currentPier);

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

    private void reloadOnShip(Ship ship) {
        Exchanger loader = ship.getLoader();
        List<Container> currentShipCargo = ship.getContainers();
        try {
            List<Container> containers = (List<Container>) loader.exchange(currentShipCargo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
