package ru.mooncess.Pizzeria.repositories.snack;

import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Snack;

import java.util.List;

@Repository
public interface SortSnackByPrice {
    List<Snack> findByOrderByPriceAsc();
    List<Snack> findByOrderByPriceDesc();
}
