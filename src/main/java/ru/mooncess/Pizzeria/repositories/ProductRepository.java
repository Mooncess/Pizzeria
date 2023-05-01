package ru.mooncess.Pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mooncess.Pizzeria.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
