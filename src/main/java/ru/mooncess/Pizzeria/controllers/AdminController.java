package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertCreateDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkDTO;
import ru.mooncess.Pizzeria.dto.order.OrderDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackCreateDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackDTO;
import ru.mooncess.Pizzeria.entities.Order;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;
import ru.mooncess.Pizzeria.services.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final AdditiveService additiveService;
    private final DessertService dessertService;
    private final DrinkService drinkService;
    private final PizzaService pizzaService;
    private final SnackService snackService;
    private final OrderService orderService;

    @GetMapping(value = "/")
    public String getAdminMenu(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/admin_mode";
    }

    @PostMapping(value = "/additive/")
    @ResponseBody
    public ResponseEntity<AdditiveDTO> addAddtive(@RequestBody AdditiveCreateDTO additive) {
        return ResponseEntity.ok(additiveService.create(additive));
    }

    @PostMapping(value = "/additive/{id}")
    @ResponseBody
    public String deleteAdditive(@PathVariable Long id) {
        additiveService.delete(id);
        return "redirect:/additive/list";
    }

    @PostMapping(value = "/dessert/")
    @ResponseBody
    public ResponseEntity<DessertDTO> addDessert(@RequestBody DessertCreateDTO dessert) {
        return ResponseEntity.ok(dessertService.create(dessert));
    }

    @PostMapping(value = "/dessert/{id}")
    @ResponseBody
    public String deleteDessert(@PathVariable Long id) {
        dessertService.delete(id);
        return "redirect:/dessert/list";
    }

    @PostMapping(value = "/drink/")
    @ResponseBody
    public ResponseEntity<DrinkDTO> addDrink(@RequestBody DrinkCreateDTO drink) {
        return ResponseEntity.ok(drinkService.create(drink));
    }

    @PostMapping(value = "/drink/{id}")
    @ResponseBody
    public String deleteDrink(@PathVariable Long id) {
        drinkService.delete(id);
        return "redirect:/drink/list";
    }

    @PostMapping(value = "/pizza/")
    @ResponseBody
    public ResponseEntity<PizzaDTO> addPizza(@RequestBody PizzaCreateDTO pizza) {
        return ResponseEntity.ok(pizzaService.create(pizza));
    }

    @PostMapping(value = "/pizza/{id}")
    @ResponseBody
    public String deletePizza(@PathVariable Long id) {
        pizzaService.delete(id);
        return "redirect:/pizza/list";
    }


    @PostMapping(value = "/snack/")
    @ResponseBody
    public ResponseEntity<SnackDTO> addSnack(@RequestBody SnackCreateDTO snack) {
        return ResponseEntity.ok(snackService.create(snack));
    }

    @DeleteMapping(value = "/snack/{id}")
    @ResponseBody
    public String deleteSnack(@PathVariable Long id) {
        snackService.delete(id);
        return "redirect:/snack/list";
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping(value = "/orders/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/orders/update/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateOrderById(@PathVariable Long id, @RequestBody String orderStatus) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.valueOf(orderStatus));
        orderService.update(order);
        return ResponseEntity.noContent().build();
    }
}
