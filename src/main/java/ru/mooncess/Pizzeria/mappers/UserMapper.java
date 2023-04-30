package ru.mooncess.Pizzeria.mappers;

import org.mapstruct.Mapper;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.entities.User;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDto(User user);
}
