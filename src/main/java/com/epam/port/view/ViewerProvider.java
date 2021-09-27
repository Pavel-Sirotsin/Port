package com.epam.port.view;


import com.epam.port.view.info.InfoViewerAble;
import com.epam.port.view.info.InfoViewerImpl;
import com.epam.port.view.user.UserChoiceAble;
import com.epam.port.view.user.UserChoiceImpl;
import com.epam.port.view.menu.MenuViewerAble;
import com.epam.port.view.menu.MenuViewerImpl;


public class ViewerProvider {
    private static final ViewerProvider instance = new ViewerProvider();

    private final UserChoiceAble userChoice = new UserChoiceImpl();
    private final MenuViewerAble menuViewer = new MenuViewerImpl();
    private final InfoViewerAble infoViewer = new InfoViewerImpl();

    private ViewerProvider() {
    }

    public static ViewerProvider getInstance() {
        return instance;
    }

    public MenuViewerAble getMenuViewer() {
        return menuViewer;
    }

    public UserChoiceAble getUserChoice() {
        return userChoice;
    }

    public InfoViewerAble getInfoViewer() {
        return infoViewer;
    }
}
