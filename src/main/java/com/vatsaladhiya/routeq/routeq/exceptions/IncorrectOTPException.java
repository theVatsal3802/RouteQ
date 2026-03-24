package com.vatsaladhiya.routeq.routeq.exceptions;

public class IncorrectOTPException extends RuntimeException {
    public IncorrectOTPException(String message) {
        super(message);
    }

    public IncorrectOTPException() {
    }
}
