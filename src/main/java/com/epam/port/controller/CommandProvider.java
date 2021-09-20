package com.epam.port.controller;


import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public Command getCommand(String operation) {
        return commands.get(operation);
    }
}
