package com.epam.port.service.port_service;


import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.exception.ServiceException;


public interface PortServiceAble {

    void shipActionFilter(SeaPort port, Ship ship) throws ServiceException;

}
