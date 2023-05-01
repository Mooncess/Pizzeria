package ru.mooncess.Pizzeria.dto.orderitem;

import lombok.Data;
import ru.mooncess.Pizzeria.entities.Basket;

@Data
public class OrderItemInBasketDTO {
    private Long id;
    private String title;
    private Short quantity;
    private float price;
    private String description;
    private Basket basket;
}
