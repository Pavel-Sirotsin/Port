package com.epam.port.view;


import com.epam.port.view.impl.ClientChoiceImpl;
import com.epam.port.view.menu.MenuViewerAble;
import com.epam.port.view.menu.MenuViewerImpl;


public class ViewerProvider {
    private static final ViewerProvider instance = new ViewerProvider();

    private final ClientChoiceImpl clientChoiceImpl = new ClientChoiceImpl();
    private final MenuViewerAble menuViewer = new MenuViewerImpl();

    private ViewerProvider() {
    }

    public static ViewerProvider getInstance() {
        return instance;
    }

    public MenuViewerAble getMenuViewer() {
        return menuViewer;
    }

    public ClientChoiceImpl getClientChoice() {
        return clientChoiceImpl;
    }
}
