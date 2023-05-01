package ru.mooncess.Pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mooncess.Pizzeria.entities.Order;
import ru.mooncess.Pizzeria.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
