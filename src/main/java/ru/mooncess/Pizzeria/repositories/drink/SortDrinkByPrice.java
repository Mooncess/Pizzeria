package ru.mooncess.Pizzeria.repositories.drink;

import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Drink;
import ru.mooncess.Pizzeria.entities.Snack;

import java.util.List;

@Repository
public interface SortDrinkByPrice {
    List<Drink> findByOrderByPriceAsc();
    List<Drink> findByOrderByPriceDesc();
}