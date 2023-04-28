package ru.mooncess.Pizzeria.repositories;

import ru.mooncess.Pizzeria.entities.Pizza;

import java.util.List;

public interface FindPizzaByFilters {
    List<Pizza> findByFilters();
}
