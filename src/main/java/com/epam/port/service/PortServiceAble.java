package com.epam.port.service;


import com.epam.port.repository.model.ship.Ship;

public interface PortServiceAble {

    void showSituation();
    
    Ship registerShip();
}
