package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.dto.additive.AdditiveAddToPizzaDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.entities.Additive;

@Mapper(componentModel = "spring")
public interface AdditiveMapper {
    Additive toEntity(AdditiveCreateDTO dto);
    Additive toEntity(AdditiveAddToPizzaDTO dto);

    AdditiveDTO toDto(Additive additive);
}
