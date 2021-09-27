package com.epam.port.view.user;


import com.epam.port.repository.model.ship.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserChoiceImpl implements UserChoiceAble {
    private static final Logger logger = LogManager.getLogger(UserChoiceImpl.class);
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getMooringPermission(Ship ship) {
        System.out.println("\n\"" + ship.getName() + "\"" + ASK_MESSAGE);
        System.out.println(ALLOW_MOORING);

        while (!scanner.hasNext(PATTERN)) {
            System.err.println(SORRY_MESSAGE);
            logger.warn(INVALID_MESSAGE);
            scanner.next();
        }

        return scanner.next();

    }

    @Override
    public String getUnloadingPermission(Ship ship) {
        System.out.println("\n\"" + ship.getName() + "\"" + UNLOADING_MESSAGE);
        System.out.println(ALLOW_UNLOADING);

        while (!scanner.hasNext(PATTERN)) {
            System.err.println(SORRY_MESSAGE);
            logger.warn(INVALID_MESSAGE);
            scanner.next();
        }

        return scanner.next();
    }
}
