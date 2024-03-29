package ru.mooncess.Pizzeria.repositories.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Additive;
import ru.mooncess.Pizzeria.entities.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long>, SortDrinkByPrice {
}
