package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.exception.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologySaveDto;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import com.github.petkovicdanilo.freelance.model.mapper.TechnologiesMapper;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnologiesService {

    private final TechnologiesRepository technologiesRepository;
    private final TechnologiesMapper technologiesMapper;

    public List<TechnologyDto> getAll() {
        return technologiesRepository.findAll()
                .stream()
                .map(technologiesMapper::toDto)
                .collect(Collectors.toList());
    }

    public TechnologyDto getOne(int id) throws ResourceNotFoundException {
        TechnologyEntity technologyEntity = technologiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY));

        return technologiesMapper.toDto(technologyEntity);
    }

    public TechnologyDto save(TechnologySaveDto technology) {
        TechnologyEntity technologyEntity = technologiesRepository.save(technologiesMapper.toEntity(technology));
        return technologiesMapper.toDto(technologyEntity);
    }

    public TechnologyDto update(int id, TechnologySaveDto updatedTechnology) throws ResourceNotFoundException {
        if(!technologiesRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY);
        }

        TechnologyEntity technologyEntity = technologiesMapper.toEntity(updatedTechnology);
        technologyEntity.setId(id);

        return technologiesMapper.toDto(technologiesRepository.save(technologyEntity));
    }

    public void remove(int id) throws ResourceNotFoundException {
        if(!technologiesRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.TECHNOLOGY);
        }

        technologiesRepository.deleteById(id);
    }
}
