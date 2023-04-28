package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaWithoutAdditivesDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.entities.Pizza;

@Mapper(componentModel = "spring")
public interface PizzaMapper {
    Pizza toEntity(PizzaWithoutAdditivesDTO dto);

    PizzaWithoutAdditivesDTO toWithoutAdditives(PizzaCreateDTO dto);
    PizzaDTO toDto(Pizza pizza);
}
