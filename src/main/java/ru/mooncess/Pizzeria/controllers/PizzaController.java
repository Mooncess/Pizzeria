package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.PizzaMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.PizzaService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService service;
    private final PizzaMapper mapper;
    private final UserRepository userRepository;
    private final BasketService basketService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<PizzaDTO>> getAll(@RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
        if (sortPrice == 1) {
            return ResponseEntity.ok(service.findByOrderByPriceAsc().stream().map(mapper::toDto).collect(Collectors.toList()));
        }
        else if (sortPrice == 2) {
            return ResponseEntity.ok(service.findByOrderByPriceDesc().stream().map(mapper::toDto).collect(Collectors.toList()));
        }
        else {
            return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addToBasket/{id}")
    public ResponseEntity<Boolean> addToBasket(@PathVariable Long id, @RequestBody OrderItemForCotroller orderItemForCotroller,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        basketService.addToBasket(user, id, orderItemForCotroller);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
