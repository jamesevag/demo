package de.adesso.demo.exception;

public class UserNotFoundException extends DemoAppException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
