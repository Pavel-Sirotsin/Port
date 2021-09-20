package com.epam.port.runner;


import com.epam.port.view.ViewerProvider;

public class Main {
    public static void main(String[] args){
        ViewerProvider.getInstance().getMenuViewer().runApp();

    }
}
