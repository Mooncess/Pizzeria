package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.pizza.PizzaCreateDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaWithoutAdditivesDTO;
import ru.mooncess.Pizzeria.dto.pizza.PizzaDTO;
import ru.mooncess.Pizzeria.entities.Additive;
import ru.mooncess.Pizzeria.entities.Pizza;
import ru.mooncess.Pizzeria.mappers.PizzaMapper;
import ru.mooncess.Pizzeria.repositories.PizzaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {
    private final PizzaRepository repository;
    private final PizzaMapper mapper;
    private AdditiveService additiveService;

    public PizzaDTO create(PizzaCreateDTO pizzaDTO){
        PizzaWithoutAdditivesDTO pizzaWithoutAdditivesDTO = mapper.toWithoutAdditives(pizzaDTO);
        List<Additive> additives = new ArrayList<Additive>();
        for (Long i : pizzaDTO.getAvailableAdditive()) {
            additives.add(additiveService.findById(i));
        }
        Pizza pizza = mapper.toEntity(pizzaWithoutAdditivesDTO);
        pizza.setAvailableAdditive(additives);
        repository.save(pizza);
        return mapper.toDto(pizza);
    }

    public List<Pizza> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
