package com.vatsaladhiya.routeq.routeq.exceptions;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String message) {
        super(message);
    }

    public UnauthenticatedUserException() {}
}
