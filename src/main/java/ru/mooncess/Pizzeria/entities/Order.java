package ru.mooncess.Pizzeria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_pizzeria")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String creationDate;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private Float total;

    private String description;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
}