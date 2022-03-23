package com.github.petkovicdanilo.freelance.controller.impl;

import com.github.petkovicdanilo.freelance.controller.TechnologiesRestController;
import com.github.petkovicdanilo.freelance.model.Technology;
import com.github.petkovicdanilo.freelance.service.TechnologiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TechnologiesRestControllerImpl implements TechnologiesRestController {

    private final TechnologiesService technologiesService;

    @Override
    public List<Technology> getTechnologies() {
        return this.technologiesService.getAll();
    }

    @Override
    public Technology getTechnology(int id) {
        return this.technologiesService.getOne(id);
    }

    @Override
    public Technology saveTechnology(Technology technology) {
        return this.technologiesService.save(technology);
    }

    @Override
    public Technology updateTechnology(int id, Technology technology) {
        return this.technologiesService.update(id, technology);
    }

    @Override
    public Technology removeTechnology(int id) {
        return this.technologiesService.remove(id);
    }
}
