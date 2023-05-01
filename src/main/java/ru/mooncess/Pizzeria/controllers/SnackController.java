package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.dto.snack.SnackCreateDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackDTO;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.mappers.SnackMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.AdditiveService;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.SnackService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/snack")
public class SnackController {
    private final SnackService service;
    private final SnackMapper mapper;
    private final UserRepository userRepository;
    private final BasketService basketService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<SnackDTO>> getAll(@RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
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
        System.out.println(userDetails.getUsername());
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        System.out.println(user.getId());
        basketService.addToBasket(user, id, orderItemForCotroller);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
