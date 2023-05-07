package ru.mooncess.Pizzeria.controllers;

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
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.PizzaMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.PizzaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService service;
    private final PizzaMapper mapper;
    private final UserRepository userRepository;
    private final BasketService basketService;

    @GetMapping(value = {"/pizza/list", "/"})
    public String getAll(Authentication authentication, Model model, @RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
        List<PizzaDTO> pizzaList;
        if (sortPrice == 1) {
            pizzaList = service.findByOrderByPriceAsc().stream().map(mapper::toDto).toList();
        }
        else if (sortPrice == 2) {
            pizzaList = service.findByOrderByPriceDesc().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        else {
            pizzaList = service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        model.addAttribute("allPizza", pizzaList);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);

        return "pizza";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/pizza/addToBasket/{id}")
    public String addToBasket(@PathVariable Long id, @RequestParam ("quantity") Short quantity,
                              @RequestParam ("pizzaSize") String pizzaSize,
                              @RequestParam ("pizzaDough") String pizzaDough,
                              @RequestParam (name = "selectedAdditives", required = false) List<Long> selectedAdditives,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        OrderItemForCotroller orderItemForCotroller = new OrderItemForCotroller();
        orderItemForCotroller.setSize(pizzaSize);
        orderItemForCotroller.setDough(pizzaDough);
        orderItemForCotroller.setAdditivesId(selectedAdditives);
        orderItemForCotroller.setQuantity(quantity);
        basketService.addToBasket(user, id, orderItemForCotroller);
        return "redirect:/pizza/list";
    }
}
