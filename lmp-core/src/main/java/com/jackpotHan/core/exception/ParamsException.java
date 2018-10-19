package com.jackpotHan.core.exception;

public class ParamsException extends RuntimeException {

    public ParamsException(String message) {
        super(message);
    }

    public ParamsException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
