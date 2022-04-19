package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.TechnologiesRestController;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologySaveDto;
import com.github.petkovicdanilo.freelance.service.TechnologiesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Technologies")
@RequiredArgsConstructor
public class TechnologiesRestControllerImpl implements TechnologiesRestController {

    private final TechnologiesService technologiesService;

    @Override
    public List<TechnologyDto> getTechnologies() {
        return this.technologiesService.getAll();
    }

    @Override
    public TechnologyDto getTechnology(int id) throws ResourceNotFoundException {
        return this.technologiesService.getOne(id);
    }

    @Override
    public TechnologyDto saveTechnology(TechnologySaveDto technology) {
        return this.technologiesService.save(technology);
    }

    @Override
    public TechnologyDto updateTechnology(int id, TechnologySaveDto technology) throws ResourceNotFoundException {
        return this.technologiesService.update(id, technology);
    }

    @Override
    public void removeTechnology(int id) throws ResourceNotFoundException {
        this.technologiesService.remove(id);
    }
}
