package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.mappers.DessertMapper;
import ru.mooncess.Pizzeria.repositories.UserRepository;
import ru.mooncess.Pizzeria.services.BasketService;
import ru.mooncess.Pizzeria.services.DessertService;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dessert")
public class DessertController {
    private final DessertService service;
    private final DessertMapper mapper;
    private final UserRepository userRepository;
    private final BasketService basketService;

    @GetMapping(value = "/list")
    public String getAll(Authentication authentication, Model model, @RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
        List<DessertDTO> dessertList;
        if (sortPrice == 1) {
            dessertList = service.findByOrderByPriceAsc().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        else if (sortPrice == 2) {
            dessertList = service.findByOrderByPriceDesc().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        else {
            dessertList = service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        model.addAttribute("allDessert", dessertList);

        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "dessert";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addToBasket/{id}")
    public String addToBasket(@PathVariable Long id, @RequestParam ("quantity") Short quantity,
                               @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        OrderItemForCotroller orderItemForCotroller = new OrderItemForCotroller();
        orderItemForCotroller.setQuantity(quantity);
        basketService.addToBasket(user, id, orderItemForCotroller);
        return "redirect:/dessert/list";
    }
}
