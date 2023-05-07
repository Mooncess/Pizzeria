package ru.mooncess.Pizzeria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.order.OrderDTO;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemDTO;
import ru.mooncess.Pizzeria.dto.user.UserDTO;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.OrderItemMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.OrderService;
import ru.mooncess.Pizzeria.services.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;
    private final UserRepository userRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;
    @PostMapping("/registration")
    public boolean register(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/basket")
    public String getBasket(Authentication authentication, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        List<OrderItemDTO> allItem = basketService.getItemsInBasketByUser(user).stream().map(orderItemMapper::toDto).toList();
        float total = 0;
        for (OrderItemDTO i : allItem) {
            total += i.getPrice() * i.getQuantity();
        }
        model.addAttribute("allItem", allItem);
        model.addAttribute("total", total);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);

        return "user/basket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/basket/deleteItem/{itemId}")
    public String deleteFromBasket(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long itemId) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        basketService.deleteFromBasket(user, itemId);
        return "redirect:/user/basket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/basket/confirm")
    public String confirmOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestParam ("address") String address) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        orderService.create(user, address);
        return "redirect:/user/order";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order")
    public String getAllOrderByBuyerId(Authentication authentication, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        List<OrderDTO> allOrder = orderService.findAllByBuyerId(user);
        Collections.reverse(allOrder);
        model.addAttribute("allOrder", allOrder);
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "user/order";
    }
}
