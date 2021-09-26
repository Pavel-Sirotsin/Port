package com.epam.port.service.port_service;


import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.exception.ServiceException;


public interface PortServiceAble {
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";

    String LEAVE_AREA = " is living the SeaPort water area.";
    String DENIED_DOCK = String.format("%20s %s %s", RED, "DENIED to dock", RESET);


    void shipAction(SeaPort port, Ship ship) throws ServiceException;

}
