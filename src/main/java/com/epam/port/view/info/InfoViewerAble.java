package com.epam.port.view.info;

import com.epam.port.repository.model.pier.Pier;
import com.epam.port.repository.model.ship.Ship;

public interface InfoViewerAble {
    String CYAN = "\u001B[36m";
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String ORANGE = "\u001B[33m";

    String MOVE_UNLOAD = " is going to unload containers on:\n";
    String SHIP_CARGO = "Ship onboard state: ";
    String SUCCEED = String.format("%20s %s %s", CYAN, "SUCCEED", RESET);
    String LEAVE_AREA = " is living the SeaPort water area.";
    String DONE = "Unloaded Cargo (container): ";
    String DENIED_DOCK = String.format("%20s %s %s", RED, "DENIED to dock", RESET);
    String WAIT = String.format("%20s %s %s", ORANGE, "WAITING", RESET);
    String RELOAD_SHIP = " waiting for a ship to reload!";
    String RELOAD_DONE = " has reload Cargo in the waiting area: ";

    void showMessage(String value);

    void showAcceptPermissionResult(Ship ship, Pier pier) throws InterruptedException;

    void showDeniedPermissionResult(Ship ship) throws InterruptedException;

    void showAcceptUnloadingResult(Ship ship, Pier pier, int result) throws InterruptedException;

    void showDeniedUnloadingResult(Ship ship) throws InterruptedException;

    void showReloadingResult(Ship ship) throws InterruptedException;
}
