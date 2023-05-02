package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.dto.order.OrderDTO;
import ru.mooncess.Pizzeria.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Order order);
    Order toEntity(OrderDTO dto);
}
