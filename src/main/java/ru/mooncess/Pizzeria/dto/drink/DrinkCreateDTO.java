package ru.mooncess.Pizzeria.dto.drink;

import lombok.Data;

@Data
public class DrinkCreateDTO {
    private String title;
    protected Float price;
    private String description;
    private float volume;
}
