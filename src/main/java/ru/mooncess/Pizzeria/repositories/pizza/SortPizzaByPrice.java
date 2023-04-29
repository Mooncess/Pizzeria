package ru.mooncess.Pizzeria.repositories.pizza;

import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Pizza;

import java.util.List;

@Repository
public interface SortPizzaByPrice {
    List<Pizza> findByOrderByPriceAsc();
    List<Pizza> findByOrderByPriceDesc();
}
