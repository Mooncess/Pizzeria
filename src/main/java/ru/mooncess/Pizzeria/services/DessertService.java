package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.dessert.DessertCreateDTO;
import ru.mooncess.Pizzeria.dto.dessert.DessertDTO;
import ru.mooncess.Pizzeria.entities.Dessert;
import ru.mooncess.Pizzeria.entities.Drink;
import ru.mooncess.Pizzeria.mappers.DessertMapper;
import ru.mooncess.Pizzeria.repositories.dessert.DessertRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DessertService {
    private final DessertRepository repository;
    private final DessertMapper mapper;

    public DessertDTO create(DessertCreateDTO dessert){
        return mapper.toDto(repository.save(mapper.toEntity(dessert)));
    }

    public List<Dessert> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Dessert findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Dessert> findByOrderByPriceAsc(){
        return repository.findByOrderByPriceAsc();
    }

    public List<Dessert> findByOrderByPriceDesc(){
        return repository.findByOrderByPriceDesc();
    }
}
