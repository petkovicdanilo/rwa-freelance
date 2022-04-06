package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
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

    public Technology getOne(int id) throws ResourceNotFoundException {
        return technologiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY));
    }

    public Technology save(Technology technology) {
        technologiesRepository.save(technology);
        return technology;
    }

    public Technology update(int id, Technology updatedTechnology) throws ResourceNotFoundException {
        Technology technology = technologiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY));

        technology.setId(updatedTechnology.getId());
        technology.setName(updatedTechnology.getName());

        return technology;
    }

    public void remove(int id) throws ResourceNotFoundException {
        if(!technologiesRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY);
        }

        technologiesRepository.deleteById(id);
    }
}
