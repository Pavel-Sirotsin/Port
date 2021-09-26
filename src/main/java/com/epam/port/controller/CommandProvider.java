package com.epam.port.controller;


import com.epam.port.controller.impl.RunPortSimulator;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        this.commands.put("1", new RunPortSimulator());
    }

    public Command getCommand(String operation) {
        return commands.get(operation);
    }
}
