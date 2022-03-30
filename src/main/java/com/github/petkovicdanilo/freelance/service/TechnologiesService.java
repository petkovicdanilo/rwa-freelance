package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.Technology;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologiesService {

    private final TechnologiesRepository technologiesRepository;

    public List<Technology> getAll() {
        return technologiesRepository.findAll();
    }

    public Technology getOne(int id) {
        return technologiesRepository.findById(id).orElse(null);
    }

    public Technology save(Technology technology) {
        technologiesRepository.save(technology);
        return technology;
    }

    public Technology update(int id, Technology updatedTechnology) {
        Technology technology = technologiesRepository.findById(id).orElse(null);

        technology.setId(updatedTechnology.getId());
        technology.setName(updatedTechnology.getName());

        return technology;
    }

    public void remove(int id) {
        if(!technologiesRepository.existsById(id)) {
            return;
        }

        technologiesRepository.deleteById(id);
    }
}
