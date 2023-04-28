package ru.mooncess.Pizzeria.dto.pizza;

import lombok.Data;
import ru.mooncess.Pizzeria.entities.Additive;

import java.util.List;

@Data
public class PizzaWithoutAdditivesDTO {
    private String title;
    protected Float price;
    private String description;
    private List<String> availableSize;
    private List<String> availableDough;
}
