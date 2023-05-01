package ru.mooncess.Pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Basket;
import ru.mooncess.Pizzeria.entities.Order;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
