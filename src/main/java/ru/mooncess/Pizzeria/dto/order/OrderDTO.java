package ru.mooncess.Pizzeria.dto.order;

import jakarta.persistence.*;
import lombok.Data;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String address;
    private String creationDate;
    private String description;
    private String status;
    private Float total;
}
