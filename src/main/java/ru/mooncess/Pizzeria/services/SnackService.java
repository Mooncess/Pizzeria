package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.snack.SnackCreateDTO;
import ru.mooncess.Pizzeria.dto.snack.SnackDTO;
import ru.mooncess.Pizzeria.entities.Pizza;
import ru.mooncess.Pizzeria.entities.Snack;
import ru.mooncess.Pizzeria.mappers.SnackMapper;
import ru.mooncess.Pizzeria.repositories.snack.SnackRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SnackService {
    private final SnackRepository repository;
    private final SnackMapper mapper;

    public SnackDTO create(SnackCreateDTO snack){
        return mapper.toDto(repository.save(mapper.toEntity(snack)));
    }

    public List<Snack> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Snack findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Snack> findByOrderByPriceAsc(){
        return repository.findByOrderByPriceAsc();
    }

    public List<Snack> findByOrderByPriceDesc(){
        return repository.findByOrderByPriceDesc();
    }
}
