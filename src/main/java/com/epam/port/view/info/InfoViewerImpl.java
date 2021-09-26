package com.epam.port.view.info;

public class InfoViewerImpl implements InfoViewerAble {

    @Override
    public void showMessage(String value) {
        System.out.println(value);
    }
}
