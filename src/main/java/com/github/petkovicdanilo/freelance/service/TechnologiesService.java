package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.Technology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologiesService {
    private final List<Technology> technologies;

    public TechnologiesService() {
        this.technologies = new ArrayList<>();

        this.technologies.add(new Technology(1, "Java"));
        this.technologies.add(new Technology(2, "C#"));
        this.technologies.add(new Technology(3, "Spring"));
        this.technologies.add(new Technology(4, "C/C++"));
    }

    public List<Technology> getAll() {
        return this.technologies;
    }

    public Technology getOne(int id) {
        return this.technologies.get(id - 1);
    }

    public Technology save(Technology technology) {
        this.technologies.add(technology);

        return technology;
    }

    public Technology update(int id, Technology updatedTechnology) {
        Technology technology = this.getOne(id);

        technology.setId(updatedTechnology.getId());
        technology.setName(updatedTechnology.getName());

        return technology;
    }

    public Technology remove(int id) {
        Technology technology = this.getOne(id);

        this.technologies.remove(id - 1);

        return technology;
    }
}
