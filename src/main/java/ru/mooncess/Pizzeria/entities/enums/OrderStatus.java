package ru.mooncess.Pizzeria.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {
    ACCEPTED("accepted"),
    COOK("cook"),
    IN_DELIVERY("in_delivery"),
    COMPLETED("completed");

    private final String size;
}
