package ru.mooncess.Pizzeria.repositories.snack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Additive;
import ru.mooncess.Pizzeria.entities.Snack;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long>, SortSnackByPrice {
}
