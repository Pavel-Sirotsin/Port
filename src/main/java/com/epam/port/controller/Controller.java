package com.epam.port.controller;

public interface Controller {
    String SHIP_WAIT_MESSAGE = String.format("%50s\n%62s", "***** Ship table *****"
            , "(ships coming and connecting to the operator)");

    void doAction();
}
