package ru.mooncess.Pizzeria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemDTO;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.OrderItemMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;
    private final UserRepository userRepository;
    private final OrderItemMapper orderItemMapper;
    @PostMapping("/registration")
    public boolean register(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/basket")
    public ResponseEntity<List<OrderItemDTO>> getBasket(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return ResponseEntity.ok(basketService.getItemsInBasketByUser(user).stream().map(orderItemMapper::toDto).collect(Collectors.toList()));
    }
}
