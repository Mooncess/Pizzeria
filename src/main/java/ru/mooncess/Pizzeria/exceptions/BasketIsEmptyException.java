package ru.mooncess.Pizzeria.exceptions;

public class BasketIsEmptyException extends RuntimeException {
    public BasketIsEmptyException() {
        super("Sorry, your shopping cart is empty.");
    }
}
