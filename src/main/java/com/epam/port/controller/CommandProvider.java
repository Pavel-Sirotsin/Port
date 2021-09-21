package com.epam.port.controller;


import com.epam.port.controller.impl.PortShipRegistration;
import com.epam.port.controller.impl.ShowCurrentSituation;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        this.commands.put("1", new PortShipRegistration());
        this.commands.put("2", new ShowCurrentSituation());
    }

    public Command getCommand(String operation) {
        return commands.get(operation);
    }
}
