package ru.mooncess.Pizzeria.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum Size {

    SMALL("small"),
    STANDARD("standard"),
    LARGE("large"),
    DRINK("drink");

    private final String size;
}
