package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.entities.Product;
import ru.mooncess.Pizzeria.entities.Snack;
import ru.mooncess.Pizzeria.repositories.ProductRepository;
import ru.mooncess.Pizzeria.repositories.pizza.PizzaRepository;

@Service
@AllArgsConstructor
public class productService {
    private final ProductRepository repository;
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
