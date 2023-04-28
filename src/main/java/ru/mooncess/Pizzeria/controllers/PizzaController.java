package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.mappers.PizzaMapper;
import ru.mooncess.Pizzeria.services.PizzaService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {
    private final PizzaService service;
    private final PizzaMapper mapper;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<PizzaDTO> add(@RequestBody PizzaCreateDTO pizza) {
        return ResponseEntity.ok(service.create(pizza));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<PizzaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
    }
}
