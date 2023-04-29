package ru.mooncess.Pizzeria.repositories.dessert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Dessert;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long>, SortDessertByPrice {
}
