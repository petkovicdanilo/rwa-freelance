package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.model.Technology;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/technologies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TechnologiesRestController {
    @GetMapping()
    List<Technology> getTechnologies();

    @GetMapping("/{id}")
    Technology getTechnology(@PathVariable int id);

    @PostMapping()
    Technology saveTechnology(@RequestBody Technology technology);

    @PutMapping("/{id}")
    Technology updateTechnology(@PathVariable int id, @RequestBody Technology technology);

    @DeleteMapping("/{id}")
    Technology removeTechnology(@PathVariable int id);
}
