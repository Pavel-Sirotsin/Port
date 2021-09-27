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
        logger.traceEntry("getMooringPermission");
        System.out.println("\n\"" + ship.getName() + "\"" + ASK_MESSAGE);
        System.out.println(ALLOW_MOORING);

        while (!scanner.hasNext(PATTERN)) {
            System.err.println(SORRY_MESSAGE);
            logger.warn(INVALID_MESSAGE);
            scanner.next();
        }
        String result = scanner.next();
        logger.traceExit(result);

        return result;
    }

    @Override
    public String getUnloadingPermission(Ship ship) {
        logger.traceEntry("getUnloadingPermission");
        System.out.println("\n\"" + ship.getName() + "\"" + UNLOADING_MESSAGE);
        System.out.println(ALLOW_UNLOADING);

        while (!scanner.hasNext(PATTERN)) {
            System.err.println(SORRY_MESSAGE);
            logger.warn(INVALID_MESSAGE);
            scanner.next();
        }
        String result = scanner.next();
        logger.traceExit(result);

        return result;
    }
}
