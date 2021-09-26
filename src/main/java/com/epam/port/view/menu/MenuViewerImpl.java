package com.epam.port.view.menu;

import com.epam.port.controller.Controller;
import com.epam.port.controller.ControllerProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class MenuViewerImpl implements com.epam.port.view.menu.MenuViewerAble {
    public static final Logger logger = LogManager.getLogger(MenuViewerImpl.class);

    public void run() {
        ControllerProvider provider = ControllerProvider.getInstance();
        Controller controller = provider.getController();

        Scanner input = new Scanner(System.in);

        System.out.println(MENU);

        while (!input.hasNext(INPUT)) {
            System.err.println(INVALID_MESSAGE);
            input.next();
        }

        String request = input.next();

        if (request.equals("2")) {
            System.out.println(THANK_YOU);
        } else {
            controller.doAction(request);
        }
    }
}
