package com.epam.port.service.port_logic;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public interface PortLogicAble {
    String RESET = "\u001B[0m";
    String ORANGE = "\u001B[33m";
    String CYAN = "\u001B[36m";

    String MOVE_UNLOAD = " is going to unload containers on:\n";
    String LEAVE_AREA = " is living the SeaPort water area.";
    String NEW_REQUEST = String.format("\n%60s", "New requests are detected:");
    String DONE = "Unloaded Cargo (container): ";
    String SHIP_CARGO = "Ship onboard state: ";
    String SUCCEED = String.format("%20s %s %s", CYAN, "SUCCEED", RESET);;
    String EMPTY = "free";
    String DENIED_UNLOAD = String.format("%20s %s %s", ORANGE, "DENIED to unload", RESET);
    String THAT_ALL = "My Congratulations! You've done it!";

    void releasePermission(AtomicInteger permits, Semaphore dispatcher);

    void acceptPermission(SeaPort port, Ship ship) throws InterruptedException;
}
