package com.github.petkovicdanilo.freelance.controller;

import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologySaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/technologies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TechnologiesRestController {
    @GetMapping()
    @Operation(description = "Get all technologies", summary = "Get all technologies")
    List<TechnologyDto> getTechnologies();

    @GetMapping("/{id}")
    @Operation(description = "Get one technology", summary = "Get one technology")
    TechnologyDto getTechnology(@PathVariable int id) throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create technology", summary = "Create technology")
    TechnologyDto saveTechnology(@Valid @RequestBody TechnologySaveDto technology);

    @PutMapping("/{id}")
    @Operation(description = "Update technology", summary = "Update technology")
    TechnologyDto updateTechnology(@PathVariable int id, @Valid @RequestBody TechnologySaveDto technology) throws ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete technology", summary = "Delete technology")
    void removeTechnology(@PathVariable int id) throws ResourceNotFoundException;
}
