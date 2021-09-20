package com.epam.port.view.menu;

import java.util.regex.Pattern;

public interface MenuViewerAble {
    String SHOW_MENU = "Choose and Enter operation:" +
            "\n1) Find a voucher by CRITERIA." +
            "\n2) Find a voucher by COUNTRY." +
            "\n3) Show and sort vouchers by DAYS and PRICE."+
            "\n4) EXIT";
    String INVALID_MESSAGE = "Invalid value! Type 1, 2, 3 or 4!";
    String THANK_YOU = "See you later - alligator!";

    Pattern INPUT = Pattern.compile("[1-4]{1}");

    void runApp();
}
