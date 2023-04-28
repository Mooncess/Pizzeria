package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.additive.AdditiveCreateDTO;
import ru.mooncess.Pizzeria.dto.additive.AdditiveDTO;
import ru.mooncess.Pizzeria.entities.Additive;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.repositories.AdditiveRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class AdditiveService {
    private final AdditiveRepository repository;
    private final AdditiveMapper mapper;

    public AdditiveDTO create(AdditiveCreateDTO additive){
        return mapper.toDto(repository.save(mapper.toEntity(additive)));
    }

    public List<Additive> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Additive findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
