package com.epam.port.controller;


import com.epam.port.controller.impl.ControllerImpl;

public class ControllerProvider {
    private static final ControllerProvider instance = new ControllerProvider();

    Controller controller = new ControllerImpl();

    private ControllerProvider(){
    }

    public static ControllerProvider getInstance() {
        return instance;
    }

    public Controller getController(){
        return controller;
    }

}
