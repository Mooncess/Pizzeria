package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemDTO;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemInBasketDTO;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.dto.orderitem.CreateOrderItemDTO;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toEntity(CreateOrderItemDTO dto);
    OrderItemDTO toDto(OrderItem orderItem);
}