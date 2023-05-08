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
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackCreateDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackDTO;
import ru.mooncess.Pizzeria.entities.Additive;
import ru.mooncess.Pizzeria.entities.Order;
import ru.mooncess.Pizzeria.entities.Snack;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;
import ru.mooncess.Pizzeria.services.*;

import java.util.Collections;
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

    @GetMapping(value = "/additive_create")
    public String additiveCreateMenu(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/additive_create";
    }

    @GetMapping(value = "/dessert_create")
    public String dessertCreateMenu(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/dessert_create";
    }

    @GetMapping(value = "/drink_create")
    public String drinkCreateMenu(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/drink_create";
    }

    @GetMapping(value = "/pizza_create")
    public String pizzaCreateMenu(Authentication authentication, Model model) {
        List<Additive> additiveList = additiveService.findAll();
        model.addAttribute("additiveList", additiveList);
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/pizza_create";
    }

    @GetMapping(value = "snack_create")
    public String snackCreateMenu(Authentication authentication, Model model) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/snack_create";
    }

    @PostMapping(value = "/additive/")
    public String addAddtive(@RequestParam ("title") String title,
                             @RequestParam ("price") float price) {
        AdditiveCreateDTO additiveCreateDTO = new AdditiveCreateDTO();
        additiveCreateDTO.setTitle(title);
        additiveCreateDTO.setPrice(price);
        additiveService.create(additiveCreateDTO);
        return "redirect:/admin/additive_create";
    }

    @PostMapping(value = "/additive/{id}")
    public String deleteAdditive(@PathVariable Long id) {
        additiveService.delete(id);
        return "redirect:/additive/list";
    }

    @PostMapping(value = "/dessert/")
    public String addDessert(@RequestParam ("title") String title,
                             @RequestParam ("description") String description,
                             @RequestParam ("weight") float weight,
                             @RequestParam ("price") float price) {
        DessertCreateDTO dessertCreateDTO = new DessertCreateDTO();
        dessertCreateDTO.setTitle(title);
        dessertCreateDTO.setDescription(description);
        dessertCreateDTO.setWeight(weight);
        dessertCreateDTO.setPrice(price);
        dessertService.create(dessertCreateDTO);
        return "redirect:/admin/dessert_create";
    }

    @PostMapping(value = "/dessert/{id}")
    public String deleteDessert(@PathVariable Long id) {
        dessertService.delete(id);
        return "redirect:/dessert/list";
    }

    @PostMapping(value = "/drink/")
    public String addDrink(@RequestParam ("title") String title,
                           @RequestParam ("description") String description,
                           @RequestParam ("volume") float volume,
                           @RequestParam ("price") float price) {
        DrinkCreateDTO drinkCreateDTO = new DrinkCreateDTO();
        drinkCreateDTO.setTitle(title);
        drinkCreateDTO.setDescription(description);
        drinkCreateDTO.setVolume(volume);
        drinkCreateDTO.setPrice(price);
        drinkService.create(drinkCreateDTO);
        return "redirect:/admin/drink_create";
    }

    @PostMapping(value = "/drink/{id}")
    public String deleteDrink(@PathVariable Long id) {
        drinkService.delete(id);
        return "redirect:/drink/list";
    }

    @PostMapping(value = "/pizza/")
    public String addPizza(@RequestParam ("title") String title,
                           @RequestParam ("description") String description,
                           @RequestParam (name = "size", required = false) List<String> pizzaSize,
                           @RequestParam (name = "selectedAdditives", required = false) List<Long> selectedAdditives,
                           @RequestParam ("price") float price) {
        PizzaCreateDTO pizza = new PizzaCreateDTO();
        pizza.setTitle(title);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizzaSize.add("STANDARD");
        System.out.println(pizzaSize);
        pizza.setAvailableSize(pizzaSize);
        List<String> listDough = List.of("TRADITIONAL", "THIN");
        pizza.setAvailableDough(listDough);
        pizza.setAvailableAdditive(selectedAdditives);
        pizzaService.create(pizza);
        return "redirect:/admin/pizza_create";
    }

    @PostMapping(value = "/pizza/{id}")
    public String deletePizza(@PathVariable Long id) {
        pizzaService.delete(id);
        return "redirect:/pizza/list";
    }


    @PostMapping(value = "/snack/")
    public String addSnack(@RequestParam ("title") String title,
                           @RequestParam ("description") String description,
                           @RequestParam ("weight") float weight,
                           @RequestParam ("price") float price) {
        SnackCreateDTO snackCreateDTO = new SnackCreateDTO();
        snackCreateDTO.setTitle(title);
        snackCreateDTO.setDescription(description);
        snackCreateDTO.setWeight(weight);
        snackCreateDTO.setPrice(price);
        snackService.create(snackCreateDTO);
        return "redirect:/admin/snack_create";
    }

    @DeleteMapping(value = "/snack/{id}")
    public String deleteSnack(@PathVariable Long id) {
        snackService.delete(id);
        return "redirect:/snack/list";
    }

    @GetMapping("/orders")
    public String getAllOrders(Authentication authentication, Model model) {
        List<Order> allOrder = orderService.findAll();
        Collections.reverse(allOrder);
        model.addAttribute("allOrder", allOrder);
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        boolean isAdmin = false;
        if (isAuthenticated) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            isAdmin = userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));
        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin/admin_orders";
    }

    @PostMapping(value = "/orders/delete/{id}")
    public String deleteOrderById(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "/orders/update/{id}")
    public String updateOrderById(@PathVariable Long id, @RequestParam ("orderStatus") String orderStatus) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.valueOf(orderStatus));
        orderService.update(order);
        return "redirect:/admin/orders";
    }
}
