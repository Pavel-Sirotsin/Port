package com.epam.port.service.port_data;

import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;

import java.util.List;

public interface PortDataAble {
    String NAME = "The Port of Kaliningrad";

    SeaPort getPortWithData();

    List<Ship> getShipWithData();
}
