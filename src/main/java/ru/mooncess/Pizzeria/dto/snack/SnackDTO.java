package ru.mooncess.Pizzeria.dto.snack;

import lombok.Data;

@Data
public class SnackDTO {
    private Long id;
    private String title;
    protected Float price;
    private String description;
    private float weight;
}
