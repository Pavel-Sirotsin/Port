package com.epam.port.repository;


public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();


    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }



}
