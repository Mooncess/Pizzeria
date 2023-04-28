package ru.mooncess.Pizzeria.dto.pizza;

import lombok.Data;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.entities.enums.Dough;
import ru.mooncess.Pizzeria.entities.enums.Size;

import java.util.List;

@Data
public class PizzaDTO {
    private Long id;
    private String title;
    protected Float price;
    private String description;
    private List<Size> availableSize;
    private List<Dough> availableDough;
    private List<AdditiveDTO> availableAdditive;
}
