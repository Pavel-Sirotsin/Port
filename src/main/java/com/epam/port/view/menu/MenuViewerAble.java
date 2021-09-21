package com.epam.port.view.menu;

import java.util.regex.Pattern;

public interface MenuViewerAble {
    String MENU = "Welcome to Port of Klaipeda!" +
            "\n1) Ship registration." +
            "\n2) The current situation." +
            "\n4) EXIT";
    String INVALID_MESSAGE = "Invalid value! Type 1, 2 or 3!";
    String THANK_YOU = "See you later - alligator!";

    Pattern INPUT = Pattern.compile("[1-3]{1}");

    void run();
}
