package ru.mooncess.Pizzeria.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mooncess.Pizzeria.entities.Order;
import ru.mooncess.Pizzeria.entities.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByDescriptionAndBasketId(String description, Long id);
    List<OrderItem> findAllByBasketId(Long id);
}
