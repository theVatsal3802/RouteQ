package com.vatsaladhiya.routeq.routeq.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException() {
    }
}
