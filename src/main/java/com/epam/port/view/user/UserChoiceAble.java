package com.epam.port.view.user;

import com.epam.port.repository.model.ship.Ship;

import java.util.regex.Pattern;

public interface UserChoiceAble {
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String ALLOW_MOORING = "Allow to moor? Y/N";
    String SORRY_MESSAGE = "Type Y or N!";
    String ASK_MESSAGE = " to" + GREEN + " Dispatcher:" + RESET + " permission to dock.";
    String UNLOADING_MESSAGE = " to" + GREEN + " Dispatcher:" + RESET + " permission to unload operation.";
    String ALLOW_UNLOADING = "Allow to unload? Y/N";


    String INVALID_MESSAGE = "Invalid value.";

    Pattern PATTERN = Pattern.compile("[Y,N]{1}", Pattern.CASE_INSENSITIVE);

    String getMooringPermission(Ship ship);

    String getUnloadingPermission(Ship ship);

}
