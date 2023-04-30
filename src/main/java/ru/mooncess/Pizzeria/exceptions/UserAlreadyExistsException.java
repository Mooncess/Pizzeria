package ru.mooncess.Pizzeria.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Sorry, user with this login or username is already exists");
    }
}
