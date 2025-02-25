package de.adesso.demo.exception;


public class DemoAppException extends RuntimeException {
    public DemoAppException(String message) {
        super(message);
    }

    public DemoAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
