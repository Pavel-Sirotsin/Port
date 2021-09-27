package com.epam.port.controller.impl;

import com.epam.port.controller.Command;
import com.epam.port.repository.model.port.SeaPort;
import com.epam.port.repository.model.ship.Ship;
import com.epam.port.service.port_data.PortDataAble;
import com.epam.port.service.port_service.PortServiceAble;
import com.epam.port.service.ServiceProvider;
import com.epam.port.service.exception.ServiceException;
import com.epam.port.view.ViewerProvider;
import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.user.UserChoiceAble;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RunPortSimulator implements Command {
    private static final Logger logger = LogManager.getLogger(RunPortSimulator.class);
    private static final String SHIP_WAIT_MESSAGE = String.format("%50s\n%62s", "***** Ship table *****"
            , "(ships coming and connecting to the operator)");

    private PortServiceAble service = ServiceProvider.getInstance().getService();
    private PortDataAble data = ServiceProvider.getInstance().getData();
    private InfoViewerAble info = ViewerProvider.getInstance().getInfoViewer();
    private UserChoiceAble user = ViewerProvider.getInstance().getUserChoice();


    @Override
    public void execute() {
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

    }


}
