package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertCreateDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackCreateDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackDTO;
import ru.mooncess.Pizzeria.services.*;

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

    @PostMapping(value = "/additive/")
    @ResponseBody
    public ResponseEntity<AdditiveDTO> addAddtive(@RequestBody AdditiveCreateDTO additive) {
        return ResponseEntity.ok(additiveService.create(additive));
    }

    @DeleteMapping(value = "/additive/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAddtive(@PathVariable Long id) {
        additiveService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/dessert/")
    @ResponseBody
    public ResponseEntity<DessertDTO> addDessert(@RequestBody DessertCreateDTO dessert) {
        return ResponseEntity.ok(dessertService.create(dessert));
    }

    @DeleteMapping(value = "/dessert/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteDessert(@PathVariable Long id) {
        dessertService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/drink/")
    @ResponseBody
    public ResponseEntity<DrinkDTO> addDrink(@RequestBody DrinkCreateDTO drink) {
        return ResponseEntity.ok(drinkService.create(drink));
    }

    @DeleteMapping(value = "/drink/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        drinkService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/pizza/")
    @ResponseBody
    public ResponseEntity<PizzaDTO> addPizza(@RequestBody PizzaCreateDTO pizza) {
        return ResponseEntity.ok(pizzaService.create(pizza));
    }

    @DeleteMapping(value = "/pizza/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/snack/")
    @ResponseBody
    public ResponseEntity<SnackDTO> addSnack(@RequestBody SnackCreateDTO snack) {
        return ResponseEntity.ok(snackService.create(snack));
    }

    @DeleteMapping(value = "/snack/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSnack(@PathVariable Long id) {
        snackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
