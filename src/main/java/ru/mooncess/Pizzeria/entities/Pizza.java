package ru.mooncess.Pizzeria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mooncess.Pizzeria.entities.enums.Dough;
import ru.mooncess.Pizzeria.entities.enums.Size;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends Product{
    @Enumerated(value = EnumType.STRING)
    private List<Size> availableSize;
    @Enumerated(value = EnumType.STRING)
    private List<Dough> availableDough;

    @ManyToMany
    private List<Additive> availableAdditive;
}

