package ru.mooncess.Pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
}
