package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.dessert.DessertCreateDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkDTO;
import ru.mooncess.Pizzeria.entities.Dessert;
import ru.mooncess.Pizzeria.entities.Drink;
import ru.mooncess.Pizzeria.mappers.DessertMapper;
import ru.mooncess.Pizzeria.mappers.DrinkMapper;
import ru.mooncess.Pizzeria.repositories.DessertRepository;
import ru.mooncess.Pizzeria.repositories.DrinkRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DrinkService {
    private final DrinkRepository repository;
    private final DrinkMapper mapper;

    public DrinkDTO create(DrinkCreateDTO drink){
        return mapper.toDto(repository.save(mapper.toEntity(drink)));
    }

    public List<Drink> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Drink findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
