package ru.mooncess.Pizzeria.dto.orderitem;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private String title;
    private Short quantity;
    private float price;
    private String description;
}
