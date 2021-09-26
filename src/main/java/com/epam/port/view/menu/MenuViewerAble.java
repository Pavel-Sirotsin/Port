package com.epam.port.view.menu;

import java.util.regex.Pattern;

public interface MenuViewerAble {
    String MENU = "Welcome to the simple SeaPort's Dispatcher simulator.\n" +
            "Manage by ships and unloading. " +
            "One Dispatcher serves 5 ships at a time. " +
            "The Port has 3 piers."+
            "\n1) Start simulator." +
            "\n2) EXIT";
    String INVALID_MESSAGE = "Invalid value! Type 1 or 2!";
    String THANK_YOU = "See you later - alligator!";

    Pattern INPUT = Pattern.compile("[1-2]{1}");

    void run();
}
