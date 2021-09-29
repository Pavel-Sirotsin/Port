package com.epam.port.controller.impl;

import com.epam.port.controller.Controller;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.ServiceProvider;
import com.epam.port.service.exception.ServiceException;
import com.epam.port.service.port_data.PortDataAble;
import com.epam.port.service.port_service.PortServiceAble;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ControllerImpl implements Controller {
    private static final Logger logger = LogManager.getLogger(ControllerImpl.class);

    private final PortServiceAble service = ServiceProvider.getInstance().getService();
    private final PortDataAble data = ServiceProvider.getInstance().getData();
    private final InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();

    @Override
    public void doAction() {
        logger.traceEntry("doAction");

        SeaPort port = data.getPortWithData();
        List<Ship> ships = data.getShipWithData();

        info.showMessage(SHIP_WAIT_MESSAGE);

        for (Ship ship : ships) {
            new Thread(ship) {
                @Override
                public void run() {
                    try {
                        service.shipActionFilter(port, ship);

                    } catch (ServiceException e) {
                        logger.throwing(Level.ERROR, e);

                    }
                }
            }.start();

        }

        logger.traceExit();
    }

}
