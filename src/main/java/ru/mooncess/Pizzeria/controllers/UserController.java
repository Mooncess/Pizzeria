package ru.mooncess.Pizzeria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.entities.Basket;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;
    @PostMapping("/registration")
    public boolean register(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/basket")
    public Basket getBasket(@AuthenticationPrincipal User user) {
        return basketService.getBasketByUser(user);
    }
}
