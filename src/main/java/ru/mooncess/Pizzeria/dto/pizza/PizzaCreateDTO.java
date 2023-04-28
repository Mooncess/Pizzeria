package ru.mooncess.Pizzeria.dto.pizza;

import lombok.Data;

import java.util.List;

@Data
public class PizzaCreateDTO {
    private String title;
    protected Float price;
    private String description;
    private List<String> availableSize;
    private List<String> availableDough;
    private List<Long> availableAdditive;
}
