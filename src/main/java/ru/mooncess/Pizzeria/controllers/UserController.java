package ru.mooncess.Pizzeria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/registration")
    public boolean register(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }
}
