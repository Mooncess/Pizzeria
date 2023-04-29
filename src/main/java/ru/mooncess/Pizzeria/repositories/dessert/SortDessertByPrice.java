package ru.mooncess.Pizzeria.repositories.dessert;

import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Dessert;
import ru.mooncess.Pizzeria.entities.Drink;

import java.util.List;

@Repository
public interface SortDessertByPrice {
    List<Dessert> findByOrderByPriceAsc();
    List<Dessert> findByOrderByPriceDesc();
}
