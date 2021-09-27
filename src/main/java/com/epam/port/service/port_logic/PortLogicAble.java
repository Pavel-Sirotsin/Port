package com.epam.port.service.port_logic;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public interface PortLogicAble {
    String NEW_REQUEST = String.format("\n%60s", "New requests are detected:");
    String EMPTY = "free";
    String THAT_ALL = String.format("\n%60s", "My Congratulations! You've done it!");


    void releasePermission(AtomicInteger permits, Semaphore dispatcher);

    void acceptPermission(SeaPort port, Ship ship, ReentrantLock locker) throws InterruptedException;
}
