package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.dto.orderitem.CreateOrderItemDTO;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toEntity(CreateOrderItemDTO dto);

}