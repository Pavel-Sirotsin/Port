package com.epam.port.repository.exception;

public class RepositoryException extends Exception {
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Exception e) {
        super(message, e);
    }

    public RepositoryException(Exception e) {
        super(e);
    }


}
