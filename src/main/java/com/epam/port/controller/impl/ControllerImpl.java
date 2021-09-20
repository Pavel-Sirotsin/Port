package com.epam.port.controller.impl;

import com.epam.port.controller.CommandProvider;
import com.epam.port.controller.Command;
import com.epam.port.controller.Controller;

public class ControllerImpl implements Controller {

    private CommandProvider provider = new CommandProvider();

    @Override
    public String doAction(String operation) {
        // request form: 'number of operation'

        Command current = provider.getCommand(operation);

        return current.execute();
    }
}
