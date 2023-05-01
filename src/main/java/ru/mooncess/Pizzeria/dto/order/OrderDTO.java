package ru.mooncess.Pizzeria.dto.order;

import jakarta.persistence.*;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String address;
    private Date creationDate;
    private OrderStatus status;
    private Float total;
    private User buyer;
    private List<OrderItem> orderItems;
}
