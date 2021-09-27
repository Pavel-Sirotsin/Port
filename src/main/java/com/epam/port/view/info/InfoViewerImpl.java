package com.epam.port.view.info;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.ship.Ship;

import static java.lang.System.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class InfoViewerImpl implements InfoViewerAble {

    @Override
    public void showMessage(String value) {
        out.println(value);
    }

    @Override
    public void showAcceptPermissionResult(Ship ship, Pier pier) throws InterruptedException {
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(600);
        out.println(name + MOVE_UNLOAD + pier.toString());
        MILLISECONDS.sleep(300);
        out.println(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")");
        MILLISECONDS.sleep(300);
    }

    @Override
    public void showDeniedPermissionResult(Ship ship) throws InterruptedException {
        MILLISECONDS.sleep(300);
        out.println(DENIED_DOCK);
        out.print("\"" + ship.getName() + "\"" + LEAVE_AREA);
    }

    @Override
    public void showAcceptUnloadingResult(Ship ship, Pier pier, int result) throws InterruptedException {
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(SUCCEED);
        out.println(DONE + result);
        MILLISECONDS.sleep(300);
        out.println(pier.toString());
        MILLISECONDS.sleep(300);
        out.println(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")\n");
        MILLISECONDS.sleep(300);
        out.print(name + LEAVE_AREA);
        MILLISECONDS.sleep(600);
    }

    @Override
    public void showDeniedUnloadingResult(Ship ship) throws InterruptedException {
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(WAIT);
        MILLISECONDS.sleep(300);
        out.println(name + RELOAD_SHIP);
        MILLISECONDS.sleep(300);
    }

    @Override
    public void showReloadingResult(Ship ship) throws InterruptedException {
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(name + RELOAD_DONE + ship.getContainers().size() + " (" + Ship.CAPACITY + ")");

    }
}
