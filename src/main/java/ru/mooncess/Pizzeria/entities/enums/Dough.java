package ru.mooncess.Pizzeria.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Dough {
    TRADITIONAL("traditional"),
    THIN("thin");

    private final String dough;
}
