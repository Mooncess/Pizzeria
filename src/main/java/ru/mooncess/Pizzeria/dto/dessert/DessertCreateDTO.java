package ru.mooncess.Pizzeria.dto.dessert;

import lombok.Data;

@Data
public class DessertCreateDTO {
    private String title;
    protected Float price;
    private String description;
    private float weight;
}
