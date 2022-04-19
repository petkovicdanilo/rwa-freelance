package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologySaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/technologies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TechnologiesRestController {
    @GetMapping()
    List<TechnologyDto> getTechnologies();

    @GetMapping("/{id}")
    TechnologyDto getTechnology(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    TechnologyDto saveTechnology(@RequestBody TechnologySaveDto technology);

    @PutMapping("/{id}")
    TechnologyDto updateTechnology(@PathVariable int id, @RequestBody TechnologySaveDto technology) throws ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeTechnology(@PathVariable int id) throws ResourceNotFoundException;
}
