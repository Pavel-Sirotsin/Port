package com.epam.port.service;


import com.epam.port.service.port_data.PortDataAble;
import com.epam.port.service.port_data.PortDataImpl;
import com.epam.port.service.port_logic.PortLogicAble;
import com.epam.port.service.port_logic.PortLogicImpl;
import com.epam.port.service.port_service.PortServiceAble;
import com.epam.port.service.port_service.PortServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider INSTANCE = new ServiceProvider();

    private PortDataAble data = new PortDataImpl();
    private PortServiceAble service = new PortServiceImpl();
    private PortLogicAble logic = new PortLogicImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return INSTANCE;
    }

    public PortDataAble getData() {
        return data;
    }

    public PortServiceAble getService() {
        return service;
    }

    public PortLogicAble getLogic() {
        return logic;
    }
}
