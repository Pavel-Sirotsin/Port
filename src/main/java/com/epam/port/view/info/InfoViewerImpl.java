package com.epam.port.view.info;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.ship.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class InfoViewerImpl implements InfoViewerAble {
    private static final Logger logger = LogManager.getLogger(InfoViewerImpl.class);

    @Override
    public void showMessage(String value) {
        logger.traceEntry("showMessage");
        out.println(value);
        logger.traceExit();
    }

    @Override
    public void showAcceptPermissionResult(Ship ship, Pier pier) throws InterruptedException {
        logger.traceEntry("showAcceptPermissionResult");
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(name + MOVE_UNLOAD + pier.toString());
        MILLISECONDS.sleep(300);
        out.println(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")");
        MILLISECONDS.sleep(300);
        logger.traceExit();
    }

    @Override
    public void showDeniedPermissionResult(Ship ship) throws InterruptedException {
        logger.traceEntry("showDeniedPermissionResult");
        MILLISECONDS.sleep(300);
        out.println(DENIED_DOCK);
        out.println("\"" + ship.getName() + "\"" + LEAVE_AREA);
        logger.traceExit();
    }

    @Override
    public void showAcceptUnloadingResult(Ship ship, Pier pier, int result) throws InterruptedException {
        logger.traceEntry("showAcceptUnloadingResult");
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(SUCCEED);
        out.println(DONE + result);
        MILLISECONDS.sleep(300);
        out.println(pier.toString());
        MILLISECONDS.sleep(300);
        out.println(SHIP_CARGO + ship.getContainers().size() + " (" + Ship.CAPACITY + ")\n");
        MILLISECONDS.sleep(300);
        out.println(name + LEAVE_AREA);
        MILLISECONDS.sleep(300);
        logger.traceExit();
    }

    @Override
    public void showDeniedUnloadingResult(Ship ship) throws InterruptedException {
        logger.traceEntry("showDeniedUnloadingResult");
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(WAIT);
        MILLISECONDS.sleep(300);
        out.println(name + RELOAD_SHIP);
        MILLISECONDS.sleep(300);
        logger.traceExit();
    }

    @Override
    public void showReloadingResult(Ship ship) throws InterruptedException {
        logger.traceEntry("showReloadingResult");
        String name = "\"" + ship.getName() + "\"";
        MILLISECONDS.sleep(300);
        out.println(name + RELOAD_DONE + ship.getContainers().size() + " (" + Ship.CAPACITY + ")");
        logger.traceExit();
    }
}
