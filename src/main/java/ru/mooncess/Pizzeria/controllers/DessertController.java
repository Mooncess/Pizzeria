package ru.mooncess.Pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertCreateDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.mappers.DessertMapper;
import ru.mooncess.Pizzeria.services.AdditiveService;
import ru.mooncess.Pizzeria.services.DessertService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dessert")
public class DessertController {
    private final DessertService service;
    private final DessertMapper mapper;

    @GetMapping(value = "/list")
    public ResponseEntity<List<DessertDTO>> getAll(@RequestParam(name = "sort", required = false, defaultValue = "0") Integer sortPrice) {
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
