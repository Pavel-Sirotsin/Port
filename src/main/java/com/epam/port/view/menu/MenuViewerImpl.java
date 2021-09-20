package com.epam.port.view.menu;

import com.epam.port.controller.Controller;
import com.epam.port.controller.ControllerProvider;
import com.epam.port.view.impl.ShowResultToClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class MenuViewerImpl implements com.epam.port.view.menu.MenuViewerAble {
    public static final Logger logger = LogManager.getLogger(MenuViewerImpl.class);

    public void runApp() {
        ControllerProvider provider = ControllerProvider.getInstance();
        Controller controller = provider.getController();
        ShowResultToClient result = new ShowResultToClient();

        Scanner input = new Scanner(System.in);

        System.out.println(SHOW_MENU);

        while (!input.hasNext(INPUT)) {
            System.err.println(INVALID_MESSAGE);
            input.next();
        }

        String request = input.next();

        if (request.equals("4")) {
            System.out.println(THANK_YOU);
        } else {
//            result.show(controller.doAction(request));
        }
    }
}
