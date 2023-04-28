package ru.mooncess.Pizzeria.dto.additive;

import lombok.Data;
import ru.mooncess.Pizzeria.entities.Pizza;

import java.util.List;

@Data
public class AdditiveDTO {
    private Long id;
    private String title;
    private Float price;
}
