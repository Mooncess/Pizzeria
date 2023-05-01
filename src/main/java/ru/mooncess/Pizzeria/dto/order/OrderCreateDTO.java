package ru.mooncess.Pizzeria.dto.order;

import lombok.Data;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemInBasketDTO;

import java.util.List;

@Data
public class OrderCreateDTO {
    private String address;
    private List<OrderItemInBasketDTO> orderItems;
}
