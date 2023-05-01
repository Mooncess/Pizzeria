package ru.mooncess.Pizzeria.dto.orderitem;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.mooncess.Pizzeria.entities.Basket;
import ru.mooncess.Pizzeria.entities.Order;

@Data
public class CreateOrderItemDTO {
    private String title;
    private Short quantity;
    private float price;
    private String description;

    private Basket basket;
}
