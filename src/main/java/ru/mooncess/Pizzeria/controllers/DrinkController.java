package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkDTO;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.mappers.DrinkMapper;
import ru.mooncess.Pizzeria.services.AdditiveService;
import ru.mooncess.Pizzeria.services.DrinkService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drink")
public class DrinkController {
    private final DrinkService service;
    private final DrinkMapper mapper;

    @GetMapping(value = "/list")
    public ResponseEntity<List<DrinkDTO>> getAll(@RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
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
}
