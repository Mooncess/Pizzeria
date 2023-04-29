package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.drink.DrinkCreateDTO;
import ru.mooncess.Pizzeria.dto.drink.DrinkDTO;
import ru.mooncess.Pizzeria.entities.Drink;
import ru.mooncess.Pizzeria.entities.Snack;
import ru.mooncess.Pizzeria.mappers.DrinkMapper;
import ru.mooncess.Pizzeria.repositories.drink.DrinkRepository;

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

    public List<Drink> findByOrderByPriceAsc(){
        return repository.findByOrderByPriceAsc();
    }

    public List<Drink> findByOrderByPriceDesc(){
        return repository.findByOrderByPriceDesc();
    }
}
